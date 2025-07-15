package com.jigubangbang.feed_service.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jigubangbang.feed_service.chat_service.NotificationServiceClient;
import com.jigubangbang.feed_service.mapper.CountryMapper;
import com.jigubangbang.feed_service.model.CommentDto;
import com.jigubangbang.feed_service.model.FeedDto;
import com.jigubangbang.feed_service.model.FeedImageDto;
import com.jigubangbang.feed_service.model.PostDto;
import com.jigubangbang.feed_service.model.chat_service.FeedNotificationRequestDto;
import com.jigubangbang.feed_service.service.CommentService;
import com.jigubangbang.feed_service.service.CountryService;
import com.jigubangbang.feed_service.service.FeedService;
import com.jigubangbang.feed_service.service.S3Service;
import com.jigubangbang.feed_service.service.UserService;
import com.jigubangbang.feed_service.util.DateUtils;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/feed")
public class FeedController {
    @Resource
    private FeedService feedService;

    @Resource
    private CommentService commentService;

    @Resource
    private UserService userService;

    @Resource
    private CountryService countryService;

    @Autowired
    private NotificationServiceClient notificationClient;

    @Resource
    private S3Service s3Service;

    @GetMapping("/following")
    public ResponseEntity<Map<String, Object>> getFollowingPosts(
            @RequestHeader("User-Id") String userId,
            @RequestParam int pageSize,
            @RequestParam int offset) {
        List<FeedDto> posts = feedService.getFollowingPosts(userId, pageSize, offset);
        return ResponseEntity.ok(Map.of("posts", posts));
    }

    @GetMapping("/bookmark")
    public ResponseEntity<Map<String, Object>> getBookmarkPosts(
            @RequestHeader("User-Id") String userId,
            @RequestParam int pageSize,
            @RequestParam int offset) {
        List<FeedDto> posts = feedService.getBookmarkPosts(userId, pageSize, offset);
        Map<String, Object> response = new HashMap<>();
        response.put("userId", userId);
        response.put("totalItems", posts.size());
        response.put("posts", posts);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{feedId}")
    public ResponseEntity<Map<String, Object>> getPostDetail(@RequestHeader("User-Id") String userId, @PathVariable int feedId) {
        PostDto post = feedService.getPostDetail(feedId);
        post.setLikeStatus(feedService.getPostLikeStatus(userId, feedId));
        post.setBookmarkStatus(feedService.getPostBookmarkStatus(userId, feedId));
        post.setImages(feedService.getPostImages(feedId));
        post.setFormattedStartDate(DateUtils.formatToKoreanDate(post.getStartDate()));
        post.setFormattedEndDate(DateUtils.formatToKoreanDate(post.getEndDate()));
        Map<String, Object> response = new HashMap<>();
        response.put("post", post);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> addPost(@RequestHeader("User-Id") String userId, @RequestBody PostDto dto) {
        dto.setUserId(userId);
        feedService.addVisitCountry(dto);
        boolean success = feedService.addPost(dto);

        if (success) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Post created successfully");
            int id = dto.getId();
            response.put("id", id);

            FeedDto post = new FeedDto();
            post.setId(id);
            post.setCountryName(countryService.getCountryNameById(dto.getCountryId()));
            post.setCityName(countryService.getCityNameById(dto.getCityId()));
            response.put("post", post);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Map.of("error", "Failed to create post"));
    }

    @PostMapping("/images")
    public ResponseEntity<Map<String, Object>> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String s3Url = s3Service.uploadFile(file, "feed/");
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Uploaded image successfully");
            response.put("photoUrl", s3Url);
            return ResponseEntity.ok(response);
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "Upload failed"));
        }
    }

    @PostMapping("/{feedId}/images")
    public ResponseEntity<Map<String, Object>> addPostImage(@RequestBody FeedImageDto dto) {
        boolean success = feedService.addPostImage(dto);
        if (success) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Saved image successfully");
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Map.of("error", "Failed to save image"));
    }

    @DeleteMapping("/{feedId}")
    public ResponseEntity<Map<String, Object>> deletePost(@PathVariable int feedId) {
        boolean success = feedService.deletePost(feedId);

        if (success) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Post deleted successfully");
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Map.of("error", "Failed to delete post"));
    }

    @PutMapping("/{feedId}")
    public ResponseEntity<Map<String, Object>> updatePost(@RequestBody PostDto dto) {
        boolean success = feedService.updatePost(dto);
        if (success) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Post updated successfully");
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Map.of("error", "Failed to update post"));
    }

    @PutMapping("/{feedId}/public")
    public ResponseEntity<Map<String, Object>> updatePublicStatus(@PathVariable int feedId, @RequestParam("status") boolean publicStatus) {
        boolean success = feedService.updatePublicStatus(feedId, publicStatus);
        if (success) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Post updated successfully");
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Map.of("error", "Failed to update status"));
    }

    @GetMapping("/{feedId}/comments")
    public ResponseEntity<Map<String, Object>> getComments(
            @RequestHeader("User-Id") String userId,
            @PathVariable int feedId,
            @RequestParam("limit") int limit,
            @RequestParam("offset") int offset) {
        List<CommentDto> comments = commentService.getComments(userId, feedId, limit, offset);

        Map<String, Object> response = new HashMap<>();
        response.put("feedId", feedId);
        response.put("totalItems", comments.size());
        response.put("comments", comments);
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{feedId}/comments/{commentId}/replies")
    public ResponseEntity<Map<String, Object>> getReplies(
            @RequestHeader("User-Id") String userId,
            @PathVariable int commentId) {
        List<CommentDto> comments = commentService.getReplies(userId, commentId);

        Map<String, Object> response = new HashMap<>();
        response.put("commentId", commentId);
        response.put("totalItems", comments.size());
        response.put("comments", comments);
        
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{feedId}/comments")
    public ResponseEntity<Map<String, Object>> addComment(
            @RequestHeader("User-Id") String userId,
            @RequestBody CommentDto dto) {
        dto.setUserId(userId);
        boolean success = commentService.addComment(dto);
        if (success) {
            try {
                int feedId = dto.getFeedId();
                String authorId = feedService.getPostUserById(feedId);
                String senderProfileImage = userService.getUserProfileById(userId);
                String senderNickname = userService.getUserNicknameById(userId);
                FeedNotificationRequestDto request = FeedNotificationRequestDto.builder()
                    .authorId(authorId)
                    .feedId(feedId)
                    .relatedUrl("/feed/" + feedId)
                    .senderId(userId)
                    .nickname(senderNickname)
                    .senderProfileImage(senderProfileImage)
                    .build();

                System.out.println("[FeedController] 피드 댓글 알림 발송: " + userId + "->" + authorId);
                notificationClient.createFeedCommentNotification(request);
            } catch (Exception e) {
                System.out.println("[FeedController] 댓글 알림 발송 실패: " + e.getMessage());
            }

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Comment created successfully");
            CommentDto comment = commentService.getCommentById(dto.getId());
            response.put("comment", comment);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Map.of("error", "Failed to create comment"));
    }

    @DeleteMapping("/{feedId}/comments/{commentId}")
    public ResponseEntity<Map<String, Object>> deleteComment(@PathVariable int commentId) {
        boolean success = commentService.deleteComment(commentId);
        if (success) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Comment deleted successfully");
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Map.of("error", "Failed to delete comment"));
    }

    @PostMapping("/{feedId}/comments/{commentId}/like")
    public ResponseEntity<Map<String, Object>> likeComment(@RequestHeader("User-Id") String userId, @PathVariable int commentId) {
        boolean success = commentService.likeComment(userId, commentId);
        if (success) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Comment liked successfully");
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Map.of("error", "Failed to like comment"));
    }

    @DeleteMapping("/{feedId}/comments/{commentId}/like")
    public ResponseEntity<Map<String, Object>> unlikeComment(@RequestHeader("User-Id") String userId, @PathVariable int commentId) {
        boolean success = commentService.unlikeComment(userId, commentId);
        if (success) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Comment unliked successfully");
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Map.of("error", "Failed to unlike comment"));
    }

    @PostMapping("/{feedId}/like")
    public ResponseEntity<Map<String, Object>> likePost(@RequestHeader("User-Id") String userId, @PathVariable int feedId) {
        boolean success = feedService.likePost(userId, feedId);

        if (success) {
            try {
                String authorId = feedService.getPostUserById(feedId);
                String senderProfileImage = userService.getUserProfileById(userId);
                String senderNickname = userService.getUserNicknameById(userId);
                FeedNotificationRequestDto request = FeedNotificationRequestDto.builder()
                    .authorId(authorId)
                    .feedId(feedId)
                    .relatedUrl("/feed/" + feedId)
                    .senderId(userId)
                    .nickname(senderNickname)
                    .senderProfileImage(senderProfileImage)
                    .build();

                notificationClient.createFeedLikeNotification(request);
                System.out.println("[FeedController] 피드 좋아요 알림 발송: " + userId + "->" + authorId);
            } catch (Exception e) {
                System.out.println("[FeedController] 알림 발송 실패: " + e.getMessage());
            }

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Feed liked successfully");
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Map.of("error", "Failed to like feed"));
    }

    @DeleteMapping("/{feedId}/like")
    public ResponseEntity<Map<String, Object>> unlikePost(@RequestHeader("User-Id") String userId, @PathVariable int feedId) {
        boolean success = feedService.unlikePost(userId, feedId);
        if (success) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Feed unliked successfully");
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Map.of("error", "Failed to unlike feed"));
    }

    @PostMapping("/{feedId}/bookmark")
    public ResponseEntity<Map<String, Object>> bookmarkPost(@RequestHeader("User-Id") String userId, @PathVariable int feedId) {
        boolean success = feedService.bookmarkPost(userId, feedId);
        if (success) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Bookmark created successfully");
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Map.of("error", "Failed to create bookmark"));
    }

    @DeleteMapping("/{feedId}/bookmark")
    public ResponseEntity<Map<String, Object>> removeBookmarkPost(@RequestHeader("User-Id") String userId, @PathVariable int feedId) {
        boolean success = feedService.removeBookmarkPost(userId, feedId);
        if (success) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Bookmark deleted successfully");
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Map.of("error", "Failed to delete bookmark"));
    }
}
