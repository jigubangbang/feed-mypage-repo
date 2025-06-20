package com.jigubangbang.mypage_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jigubangbang.mypage_service.mapper.ProfileMapper;
import com.jigubangbang.mypage_service.model.ProfileDto;

@Service
public class ProfileService {
    @Autowired
    private ProfileMapper profileMapper;

    public ProfileDto getProfileDto(String userId) {
        return profileMapper.getProfile(userId);
    }
}
