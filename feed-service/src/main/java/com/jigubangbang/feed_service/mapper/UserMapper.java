package com.jigubangbang.feed_service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jigubangbang.feed_service.model.UserDto;

@Mapper
public interface UserMapper {
    public String getUserProfileById(String userId);
    public String getUserNicknameById(String userId);
    public String getUserStyleById(String userId);
    public List<UserDto> getFriendRecommendations(@Param("userId") String userId, @Param("limit") int limit, @Param("offset") int offset);
    public List<UserDto> getUserByKeyword(@Param("keyword") String keyword, @Param("limit") int limit);
}
