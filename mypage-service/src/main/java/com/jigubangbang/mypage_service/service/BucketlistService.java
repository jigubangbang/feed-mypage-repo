package com.jigubangbang.mypage_service.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jigubangbang.mypage_service.mapper.BucketlistMapper;
import com.jigubangbang.mypage_service.model.BucketlistDto;

@Service
public class BucketlistService {
    @Autowired
    private BucketlistMapper bucketlistMapper;

    public List<BucketlistDto> getBucketlist(String userId, Boolean completionStatus) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("completionStatus", completionStatus);

        return bucketlistMapper.getBucketlist(map);
    }

    public boolean addBucketlist(BucketlistDto dto) {
        return bucketlistMapper.addBucketlist(dto) > 0;
    }

    public boolean deleteBucketlist(int bucketlistId) {
        return bucketlistMapper.deleteBucketlist(bucketlistId) > 0;
    }

    public boolean checkBucketlist(int bucketlistId) {
        return bucketlistMapper.checkBucketlist(bucketlistId) > 0;
    }

    public void updateDisplayOrder(List<BucketlistDto> orderList) {
        for(BucketlistDto dto : orderList) {
            bucketlistMapper.updateDisplayOrder(dto);
        }
    }

    public int getCompleteItemsCount(String userId) {
        return bucketlistMapper.getCompleteItemsCount(userId);
    }

    public int getIncompleteItemsCount(String userId) {
        return bucketlistMapper.getIncompleteItemsCount(userId);
    }
}
