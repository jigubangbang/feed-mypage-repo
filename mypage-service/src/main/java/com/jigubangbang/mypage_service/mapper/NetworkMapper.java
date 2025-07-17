package com.jigubangbang.mypage_service.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.jigubangbang.mypage_service.model.UserDto;

@Mapper
public interface NetworkMapper {
    public List<UserDto> getFollowingList(Map<String, Object> map);
    public List<UserDto> getFollowerList(Map<String, Object> map);
    public int getFollowingCount(String userId);
    public int getFollowerCount(String userId);
}