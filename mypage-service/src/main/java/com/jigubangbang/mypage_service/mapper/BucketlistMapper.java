package com.jigubangbang.mypage_service.mapper;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.jigubangbang.mypage_service.model.BucketlistDto;

@Mapper
public interface BucketlistMapper {
    public List<BucketlistDto> getBucketlist(Map<String, Object> map);
    public int addBucketlist(BucketlistDto dto);
    public int deleteBucketlist(int id);
    public int checkBucketlist(int id);
    public int updateDisplayOrder(BucketlistDto dto);
    public int getCompleteItemsCount(String userId);
    public int getIncompleteItemsCount(String userId);
    public Timestamp getCompletedDate(int id);
}