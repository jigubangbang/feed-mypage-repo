package com.jigubangbang.feed_service.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    public String getUserProfileById(String userId);
    public String getUserNicknameById(String userId);
}
