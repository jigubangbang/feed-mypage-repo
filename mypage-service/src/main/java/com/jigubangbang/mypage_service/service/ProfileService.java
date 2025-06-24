package com.jigubangbang.mypage_service.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jigubangbang.mypage_service.mapper.ProfileMapper;
import com.jigubangbang.mypage_service.model.CountryDto;
import com.jigubangbang.mypage_service.model.LanguageDto;
import com.jigubangbang.mypage_service.model.ProfileDto;

@Service
public class ProfileService {
    @Autowired
    private ProfileMapper profileMapper;

    public ProfileDto getProfileDto(String userId) {
        ProfileDto profileDto = profileMapper.getProfile(userId);
        
        String countryId = profileDto.getNationality();
        String travelStyleId = profileDto.getTravelStyleId();

        if (countryId != null) {
            profileDto.setNationalityName(profileMapper.getCountryName(countryId));
        }

        if (travelStyleId != null) {
            profileDto.setTravelStyleName(profileMapper.getTravelStyleName(travelStyleId));
        }

        return profileDto;
    }

    public boolean updateTravelStatus(String userId, String travelStatus) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("travelStatus", travelStatus);

        return profileMapper.updateTravelStatus(map) > 0;
    }

    public boolean getFollowStatus(String followerId, String followingId) {
        Map<String, Object> map = new HashMap<>();
        map.put("followerId", followerId);
        map.put("followingId", followingId);

        return profileMapper.getFollowStatus(map); 
    }

    public List<String> getFollowers(String userId) {
        return profileMapper.getFollowers(userId);
    }
    
    public List<String> getFollowing(String userId) {
        return profileMapper.getFollowing(userId);
    }

    public boolean followUser(String followerId, String followingId) {
        Map<String, Object> map = new HashMap<>();
        map.put("followerId", followerId);
        map.put("followingId", followingId);

        return profileMapper.followUser(map) > 0;
    }

    public boolean unfollowUser(String followerId, String followingId) {
        Map<String, Object> map = new HashMap<>();
        map.put("followerId", followerId);
        map.put("followingId", followingId);

        return profileMapper.unfollowUser(map) > 0;
    }

    public List<CountryDto> getTopCountries(String userId) {
        return profileMapper.getTopCountries(userId);
    }

    public List<LanguageDto> getUserLanguages(String userId) {
        return profileMapper.getUserLanguages(userId);
    }

    public boolean addLanguage(String userId, int languageId, String proficiency) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("languageId", languageId);
        map.put("proficiency", proficiency);
        
        return profileMapper.addLanguage(map) > 0;
    }

    public boolean removeLanguage(int id) {
        return profileMapper.removeLanguage(id) > 0;
    }
}
