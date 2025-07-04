package com.jigubangbang.feed_service.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jigubangbang.feed_service.mapper.FeedMapper;
import com.jigubangbang.feed_service.model.PostBookmarkDto;
import com.jigubangbang.feed_service.model.PostDto;
import com.jigubangbang.feed_service.model.PostLikeDto;

@Service
public class FeedService {
    @Autowired
    private FeedMapper feedMapper;

    public List<PostDto> getUserPosts(String userId) {
        return feedMapper.getUserPosts(userId);
    }

    public PostDto getPostDetail(int id) {
        return feedMapper.getPostDetail(id);
    }

    public boolean getPostLikeStatus(String userId, int feedId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("feedId", feedId);
        return feedMapper.getPostLikeStatus(map) > 0;
    }

    public boolean getPostBookmarkStatus(String userId, int feedId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("feedId", feedId);
        return feedMapper.getPostBookmarkStatus(map) > 0;
    }

    public boolean addPost(PostDto dto) {
        return feedMapper.addPost(dto) > 0;
    }

    public boolean deletePost(int id) {
        return feedMapper.deletePost(id) > 0;
    }

    public boolean likePost(PostLikeDto dto) {
        return feedMapper.likePost(dto) > 0;
    }

    public boolean unlikePost(String userId, int feedId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("feedId", feedId);
        return feedMapper.unlikePost(map) > 0;
    }

    public boolean bookmarkPost(PostBookmarkDto dto) {
        return feedMapper.bookmarkPost(dto) > 0;
    }

    public boolean removeBookmarkPost(String userId, int feedId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("feedId", feedId);
        return feedMapper.removeBookmarkPost(map) > 0;
    }
}
