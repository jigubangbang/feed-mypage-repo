package com.jigubangbang.feed_service.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jigubangbang.feed_service.mapper.StyleMapper;

@Service
public class StyleService {
    @Autowired
    private StyleMapper styleMapper;

    public boolean updateUserStyle(String userId, String travelStyleId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("travelStyleId", travelStyleId);
        return styleMapper.updateUserStyle(map) > 0;
    }
    
}
