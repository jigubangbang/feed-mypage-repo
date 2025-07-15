package com.jigubangbang.feed_service.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jigubangbang.feed_service.model.FeedDto;
import com.jigubangbang.feed_service.model.UserDto;
import com.jigubangbang.feed_service.service.FeedService;
import com.jigubangbang.feed_service.service.UserService;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/feed")
public class UserFeedController {
    @Resource
    private FeedService feedService;

    @Resource
    private UserService userService;

    @GetMapping("/users/{userId}")
    public ResponseEntity<Map<String, Object>> getUserPosts(
            @PathVariable String userId,
            @RequestParam int pageSize,
            @RequestParam int offset) {
        List<FeedDto> posts = feedService.getUserPosts(userId, pageSize, offset);
        Map<String, Object> response = new HashMap<>();
        response.put("userId", userId);
        response.put("totalItems", posts.size());
        response.put("posts", posts);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/recommended/users")
    public ResponseEntity<Map<String, Object>> getFriendRecommendations(
            @RequestHeader("User-Id") String userId,
            @RequestParam("limit") int limit,
            @RequestParam("offset") int offset) {
        List<UserDto> users = userService.getFriendRecommendations(userId, limit, offset);
        String travelStyleId = userService.getUserStyleById(userId);

        Map<String, Object> response = new HashMap<>();
        response.put("users", users);
        response.put("travelStyleId", travelStyleId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search/users")
    public ResponseEntity<Map<String, Object>> getUserByKeyword(
            @RequestParam("keyword") String keyword, 
            @RequestParam("limit") int limit) {
        List<UserDto> users = userService.getUserByKeyword(keyword, limit);
        return ResponseEntity.ok(Map.of("users", users));
    }
}
