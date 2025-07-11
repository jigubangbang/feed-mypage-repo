package com.jigubangbang.feed_service.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jigubangbang.feed_service.model.FeedDto;
import com.jigubangbang.feed_service.model.HashtagDto;
import com.jigubangbang.feed_service.service.HashtagService;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/public")
public class HashtagController {
    @Resource
    private HashtagService hashtagService;

    @GetMapping("/trending")
    public ResponseEntity<Map<String, Object>> getTrendingHashtags(@RequestParam("limit") int limit) {
        List<HashtagDto> tags = hashtagService.getTrendingHashtags(limit);
        return ResponseEntity.ok(Map.of("tags", tags));
    }

    @GetMapping("/trending/{tag}")
    public ResponseEntity<Map<String, Object>> getFeedByHashtag(
            @PathVariable("tag") String tag,
            @RequestParam("pageSize") int pageSize, 
            @RequestParam("offset") int offset) {
        List<FeedDto> posts = hashtagService.getFeedByHashtag(tag, pageSize, offset);
        return ResponseEntity.ok(Map.of("posts", posts));
    }

    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> getTagsByKeyword(
            @RequestParam("keyword") String keyword,
            @RequestParam("limit") int limit) {
        List<HashtagDto> tags = hashtagService.getTagsByKeyword(keyword, limit);
        return ResponseEntity.ok(Map.of("tags", tags));
    }
}
