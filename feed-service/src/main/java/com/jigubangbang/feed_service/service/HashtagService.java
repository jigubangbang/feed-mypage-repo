package com.jigubangbang.feed_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jigubangbang.feed_service.mapper.HashtagMapper;
import com.jigubangbang.feed_service.model.FeedDto;
import com.jigubangbang.feed_service.model.HashtagDto;

@Service
public class HashtagService {
    @Autowired
    private HashtagMapper hashtagMapper;

    public List<HashtagDto> getTrendingHashtags(int limit) {
        return hashtagMapper.getTrendingHashtags(limit);
    }

    public List<FeedDto> getFeedByHashtag(String tag, int pageSize, int offset) {
        return hashtagMapper.getFeedByHashtag(tag, pageSize, offset);
    }

}
