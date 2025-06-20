package com.jigubangbang.mypage_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jigubangbang.mypage_service.model.ProfileDto;
import com.jigubangbang.mypage_service.service.ProfileService;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Resource
    private ProfileService profileService;

    @GetMapping("/{userId}")
    public ProfileDto getProfile(@PathVariable String userId) {
        // TODO: get logged in user
        return profileService.getProfileDto("aaa");
    }
}
