package com.jigubangbang.feed_service.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jigubangbang.feed_service.mapper.FeedMapper;
import com.jigubangbang.feed_service.model.FeedDto;
import com.jigubangbang.feed_service.model.FeedImageDto;
import com.jigubangbang.feed_service.model.PostDto;

@Service
public class FeedService {
    @Autowired
    private FeedMapper feedMapper;

    public List<FeedDto> getUserPosts(String userId, int pageSize, int offset) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("pageSize", pageSize);
        map.put("offset", offset);
        return feedMapper.getUserPosts(map);
    }

    public PostDto getPostDetail(int id) {
        return feedMapper.getPostDetail(id);
    }

    public String getPostUserById(int id) {
        return feedMapper.getPostUserById(id);
    }

    public List<FeedImageDto> getPostImages(int feedId) {
        return feedMapper.getPostImages(feedId);
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

    public boolean addVisitCountry(PostDto dto) {
        return feedMapper.addVisitCountry(dto) > 0;
    }

    public boolean addPostImage(FeedImageDto dto) {
        return feedMapper.addPostImage(dto) > 0;
    }

    public boolean updatePost(PostDto dto) {
        return feedMapper.updatePost(dto) > 0;
    }

    public boolean updatePublicStatus(int feedId, boolean publicStatus) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", feedId);
        map.put("publicStatus", publicStatus);
        return feedMapper.updatePublicStatus(map) > 0;
    }

    public boolean deletePost(int id) {
        return feedMapper.deletePost(id) > 0;
    }

    public boolean likePost(String userId, int feedId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("feedId", feedId);
        return feedMapper.likePost(map) > 0;
    }

    public boolean unlikePost(String userId, int feedId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("feedId", feedId);
        return feedMapper.unlikePost(map) > 0;
    }

    public boolean bookmarkPost(String userId, int feedId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("feedId", feedId);
        return feedMapper.bookmarkPost(map) > 0;
    }

    public boolean removeBookmarkPost(String userId, int feedId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("feedId", feedId);
        return feedMapper.removeBookmarkPost(map) > 0;
    }
}
