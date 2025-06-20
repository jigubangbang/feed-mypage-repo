package com.jigubangbang.mypage_service.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.jigubangbang.mypage_service.model.ProfileDto;

@Mapper
public interface ProfileMapper {
    public ProfileDto getProfile(String userId);
}
