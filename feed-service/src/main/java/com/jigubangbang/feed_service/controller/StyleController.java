package com.jigubangbang.feed_service.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jigubangbang.feed_service.service.StyleService;

import jakarta.annotation.Resource;

@RestController
@RequestMapping
public class StyleController {
    @Resource
    private StyleService styleService;

    @PutMapping("/profile/{userId}/styles/{styleId}")
    public ResponseEntity<Map<String, Object>> updateUserStyle(@RequestHeader("User-Id") String userId, @PathVariable String styleId) {
        boolean success = styleService.updateUserStyle(userId, styleId);
        if (success) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Updated travel style successfully");
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Map.of("error", "Failed to update travel style"));
    }
}
