package com.jigubangbang.feed_service.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jigubangbang.feed_service.model.FeedDto;
import com.jigubangbang.feed_service.model.FeedFilterDto;
import com.jigubangbang.feed_service.model.HashtagDto;
import com.jigubangbang.feed_service.model.PostDto;
import com.jigubangbang.feed_service.service.FeedService;
import com.jigubangbang.feed_service.service.HashtagService;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/public")
public class PublicFeedController {
    @Resource
    private FeedService feedService;

    @Resource
    private HashtagService hashtagService;

    @GetMapping("/posts")
    public ResponseEntity<Map<String, Object>> getPosts(
            @RequestParam(required = false) String countryId,
            @RequestParam(required = false) Integer cityId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false, defaultValue = "recent") String sort,
            @RequestParam(defaultValue = "8") Integer pageSize,
            @RequestParam(defaultValue = "0") Integer offset) {
        FeedFilterDto filter = new FeedFilterDto();
        filter.setCountryId(countryId);
        filter.setCityId(cityId);
        filter.setStartDate(startDate);
        filter.setEndDate(endDate);
        filter.setSort(sort);
        filter.setPageSize(pageSize);
        filter.setOffset(offset);

        List<FeedDto> posts = feedService.getPosts(filter);
        return ResponseEntity.ok(Map.of("posts", posts));
    }

    @GetMapping("/posts/top")
    public ResponseEntity<Map<String, Object>> getTopPosts(@RequestParam(defaultValue = "5") int limit) {
        List<PostDto> posts = feedService.getTopPosts(limit);
        return ResponseEntity.ok(Map.of("posts", posts));
    }

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
