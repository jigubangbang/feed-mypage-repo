package com.jigubangbang.feed_service.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StyleMapper {
    public int updateUserStyle(Map<String, Object> map);
}
