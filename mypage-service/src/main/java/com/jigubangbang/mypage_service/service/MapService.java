package com.jigubangbang.mypage_service.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jigubangbang.mypage_service.mapper.MapMapper;
import com.jigubangbang.mypage_service.model.CityDto;
import com.jigubangbang.mypage_service.model.CountryDto;
import com.jigubangbang.mypage_service.model.CountryVisitBasicDto;
import com.jigubangbang.mypage_service.model.CountryVisitDto;
import com.jigubangbang.mypage_service.model.CountryWishDto;
import com.jigubangbang.mypage_service.model.FeedPostDto;

@Service
public class MapService {
    @Autowired
    private MapMapper mapMapper;

    public String getMapInfo(String userId) {
        return mapMapper.getMapInfo(userId);
    }

    public List<CountryDto> getCountryList(String userId, String keyword) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("keyword", keyword);
        return mapMapper.getCountryList(map);
    }

    public List<CountryVisitDto> getVisitedCountries(String userId, String continent) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("continent", continent);
        return mapMapper.getVisitedCountries(map);
    }

    public List<CountryVisitBasicDto> getBasicVisitedCountries(String userId) {
        return mapMapper.getBasicVisitedCountries(userId);
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

    public boolean addVisitCountry(CountryVisitDto dto) {
        return mapMapper.addVisitCountry(dto) > 0;
    }

    public boolean removeVisitCountry(String userId, String countryId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("countryId", countryId);
        return mapMapper.removeVisitCountry(map) > 0;
    }

    public boolean addWishlistCountry(CountryWishDto dto) {
        return mapMapper.addWishlistCountry(dto) > 0;
    }

    public boolean removeWishlistCountry(String userId, String countryId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("countryId", countryId);
        return mapMapper.removeWishlistCountry(map) > 0;
    }

    public List<CityDto> getCityList(String countryId) {
        return mapMapper.getCityList(countryId);
    }

    public List<FeedPostDto> getCountryFeed(String userId, String countryId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("countryId", countryId);
        return mapMapper.getCountryFeed(map);
    }

    public boolean changeMapColor(String userId, String mapColor) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("mapColor", mapColor);
        return mapMapper.changeMapColor(map) > 0;
    }
}
