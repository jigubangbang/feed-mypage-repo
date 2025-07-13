package com.jigubangbang.feed_service.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jigubangbang.feed_service.mapper.StyleMapper;
import com.jigubangbang.feed_service.model.TravelStyleBasicDto;
import com.jigubangbang.feed_service.model.TravelStyleDto;
import com.jigubangbang.feed_service.model.TravelStyleMatchDto;

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

    public List<TravelStyleBasicDto> getStyleList() {
        return styleMapper.getStyleList();
    }
    public TravelStyleDto getStyleDetail(String id) {
        return styleMapper.getStyleDetail(id);
    }
    public List<TravelStyleMatchDto> getCompatibleStyles(String travelStyleId) {
        return styleMapper.getCompatibleStyles(travelStyleId);
    }

    public List<TravelStyleMatchDto> getIncompatibleStyles(String travelStyleId) {
        return styleMapper.getIncompatibleStyles(travelStyleId);
    }
}
