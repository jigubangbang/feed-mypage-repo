package com.jigubangbang.mypage_service.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jigubangbang.mypage_service.mapper.NetworkMapper;
import com.jigubangbang.mypage_service.model.UserDto;

@Service
public class NetworkService {
    @Autowired
    private NetworkMapper networkMapper;

    public List<UserDto> getFollowingList(String userId, int pageSize, int offset) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId); 
        map.put("pageSize", pageSize);
        map.put("offset", offset);
        return networkMapper.getFollowingList(map);
    }
    public List<UserDto> getFollowerList(String userId, int pageSize, int offset) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId); 
        map.put("pageSize", pageSize);
        map.put("offset", offset);
        return networkMapper.getFollowerList(map);
    }

    public int getFollowingCount(String userId) {
        return networkMapper.getFollowingCount(userId);
    }
    
    public int getFollowerCount(String userId) {
        return networkMapper.getFollowerCount(userId);
    }
}
