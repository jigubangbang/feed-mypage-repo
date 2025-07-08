package com.jigubangbang.feed_service.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jigubangbang.feed_service.model.FeedDto;
import com.jigubangbang.feed_service.service.FeedService;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/feed/users")
public class UserFeedController {
    @Resource
    private FeedService feedService;

    @GetMapping("/{userId}")
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
}
