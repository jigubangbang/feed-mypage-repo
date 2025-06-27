package com.jigubangbang.mypage_service.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jigubangbang.mypage_service.mapper.MapMapper;
import com.jigubangbang.mypage_service.model.CountryDto;
import com.jigubangbang.mypage_service.model.CountryVisitDto;

@Service
public class MapService {
    @Autowired
    private MapMapper mapMapper;

    public List<CountryDto> getCountryList(String keyword) {
        return mapMapper.getCountryList(keyword);
    }

    public List<CountryVisitDto> getVisitedCountries(String userId, String continent) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("continent", continent);
        return mapMapper.getVisitedCountries(map);
    }

    public List<CountryVisitDto> getWishlistCountries(String userId) {
        return mapMapper.getWishlistCountries(userId);
    }

    public List<Map<String, Object>> getVisitedCountPerContinent(String userId) {
        return mapMapper.getVisitedCountPerContinent(userId);
    }

    public List<Map<String, Object>> getTotalCountPerContinent() {
        return mapMapper.getTotalCountPerContinent();
    }

    public int getTotalCountriesCount() {
        return mapMapper.getTotalCountriesCount();
    }

    public int getUserVisitedCount(String userId) {
        return mapMapper.getUserVisitedCount(userId);
    }

    public double getUserVisitPercentile(String userId) {
        return mapMapper.getUserVisitPercentile(userId);
    }
}
