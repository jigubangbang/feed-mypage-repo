package com.jigubangbang.feed_service.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jigubangbang.feed_service.model.CommentDto;
import com.jigubangbang.feed_service.model.CommentLikeDto;
import com.jigubangbang.feed_service.model.FeedImageDto;
import com.jigubangbang.feed_service.model.PostBookmarkDto;
import com.jigubangbang.feed_service.model.PostDto;
import com.jigubangbang.feed_service.model.PostLikeDto;
import com.jigubangbang.feed_service.service.CommentService;
import com.jigubangbang.feed_service.service.FeedService;
import com.jigubangbang.feed_service.service.S3Service;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/feed")
public class FeedController {
    @Resource
    private FeedService feedService;

    @Resource
    private CommentService commentService;

    @Resource
    private S3Service s3Service;

    @GetMapping("/{feedId}")
    public ResponseEntity<Map<String, Object>> getPostDetail(@RequestHeader("User-Id") String userId, @PathVariable int feedId) {
        PostDto post = feedService.getPostDetail(feedId);
        post.setLikeStatus(feedService.getPostLikeStatus(userId, feedId));
        post.setBookmarkStatus(feedService.getPostBookmarkStatus(userId, feedId));
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
            response.put("id", dto.getId());
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

    @GetMapping("/{feedId}/comments")
    public ResponseEntity<Map<String, Object>> getComments(@RequestHeader("User-Id") String userId, @PathVariable int feedId) {
        List<CommentDto> comments = commentService.getComments(userId, feedId);

        Map<String, Object> response = new HashMap<>();
        response.put("feedId", feedId);
        response.put("totalItems", comments.size());
        response.put("comments", comments);
        
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{feedId}/comments")
    public ResponseEntity<Map<String, Object>> addComment(@RequestBody CommentDto dto) {
        boolean success = commentService.addComment(dto);
        if (success) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Comment created successfully");
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
    public ResponseEntity<Map<String, Object>> likeComment(@RequestBody CommentLikeDto dto) {
        boolean success = commentService.likeComment(dto);
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
    public ResponseEntity<Map<String, Object>> likePost(@RequestBody PostLikeDto dto) {
        boolean success = feedService.likePost(dto);
        if (success) {
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
    public ResponseEntity<Map<String, Object>> bookmarkPost(@RequestBody PostBookmarkDto dto) {
        boolean success = feedService.bookmarkPost(dto);
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
