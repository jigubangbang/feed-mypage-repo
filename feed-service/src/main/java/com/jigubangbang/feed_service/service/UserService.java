package com.jigubangbang.feed_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jigubangbang.feed_service.mapper.UserMapper;
import com.jigubangbang.feed_service.model.UserDto;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public String getUserProfileById(String userId) {
        return userMapper.getUserProfileById(userId);
    }

    public String getUserNicknameById(String userId) {
        return userMapper.getUserNicknameById(userId);
    }

    public String getUserStyleById(String userId) {
        return userMapper.getUserStyleById(userId);
    }

    public List<UserDto> getFriendRecommendations(String userId, int limit, int offset) {
        return userMapper.getFriendRecommendations(userId, limit, offset);
    }

    public List<UserDto> getUserByKeyword(String keyword, int limit) {
        return userMapper.getUserByKeyword(keyword, limit);
    }
}
