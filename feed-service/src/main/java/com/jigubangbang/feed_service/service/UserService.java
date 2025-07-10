package com.jigubangbang.feed_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jigubangbang.feed_service.mapper.UserMapper;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public String getUserProfileById(String userId) {
        return userMapper.getUserProfileById(userId);
    }
}
