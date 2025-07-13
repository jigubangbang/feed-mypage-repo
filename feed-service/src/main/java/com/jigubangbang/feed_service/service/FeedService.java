package com.jigubangbang.feed_service.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jigubangbang.feed_service.mapper.FeedMapper;
import com.jigubangbang.feed_service.mapper.HashtagMapper;
import com.jigubangbang.feed_service.model.FeedDto;
import com.jigubangbang.feed_service.model.FeedFilterDto;
import com.jigubangbang.feed_service.model.FeedImageDto;
import com.jigubangbang.feed_service.model.HashtagDto;
import com.jigubangbang.feed_service.model.PostDto;

@Service
public class FeedService {
    @Autowired
    private FeedMapper feedMapper;

    @Autowired
    private HashtagMapper hashtagMapper;

    public List<FeedDto> getPosts(FeedFilterDto filter) {
        return feedMapper.getPosts(filter);
    }

    public List<FeedDto> getUserPosts(String userId, int pageSize, int offset) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("pageSize", pageSize);
        map.put("offset", offset);
        return feedMapper.getUserPosts(map);
    }

    public List<FeedDto> getBookmarkPosts(String userId, int pageSize, int offset) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("pageSize", pageSize);
        map.put("offset", offset);
        return feedMapper.getBookmarkPosts(map);
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

    @Transactional
    public boolean addPost(PostDto dto) {
        boolean success = feedMapper.addPost(dto) > 0;
        if (!success) return false;

        List<String> hashtags = extractHashtags(dto.getTitle());
        for (String tagName : hashtags) {
            HashtagDto tag = hashtagMapper.findHashtagByName(tagName);
            if (tag == null) {
                tag = new HashtagDto();
                tag.setName(tagName);
                hashtagMapper.insertHashtag(tag);
            } else {
                hashtagMapper.incrementHashtagCount(tag.getId());
            }
            hashtagMapper.insertFeedHashtag(dto.getId(), tag.getId());
        }
        return true;
    }

    public boolean addVisitCountry(PostDto dto) {
        return feedMapper.addVisitCountry(dto) > 0;
    }

    public boolean addPostImage(FeedImageDto dto) {
        return feedMapper.addPostImage(dto) > 0;
    }

    public boolean updatePost(PostDto dto) {
        boolean success = feedMapper.updatePost(dto) > 0;
        if (!success) return false;

        List<String> hashtags = extractHashtags(dto.getTitle());
        hashtagMapper.deleteFeedHashtag(dto.getId());
        for (String tagName : hashtags) {
            HashtagDto tag = hashtagMapper.findHashtagByName(tagName);
            if (tag == null) {
                tag = new HashtagDto();
                tag.setName(tagName);
                hashtagMapper.insertHashtag(tag);
            } else {
                hashtagMapper.incrementHashtagCount(tag.getId());
            }
            hashtagMapper.insertFeedHashtag(dto.getId(), tag.getId());
        }
        return true;

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

    public List<String> extractHashtags(String content) {
        Pattern pattern = Pattern.compile("#([\\w가-힣]+)");
        Matcher matcher = pattern.matcher(content);
        Set<String> hashtags = new HashSet<>();
        while (matcher.find()) {
            hashtags.add(matcher.group(1).toLowerCase());
        }
        return new ArrayList<>(hashtags);
    }
}
