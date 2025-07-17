package com.jigubangbang.mypage_service.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jigubangbang.mypage_service.model.UserDto;
import com.jigubangbang.mypage_service.service.NetworkService;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/profile")
public class NetworkController {
    @Resource
    private NetworkService networkService;

    @GetMapping("/{userId}/network")
    public ResponseEntity<Map<String, Object>> getFollowingList(
            @PathVariable String userId, 
            @RequestParam("type") String type,
            @RequestParam("pageSize") int pageSize,
            @RequestParam("offset") int offset) {
        List<UserDto> list = new ArrayList<>();
        int total = 0;
        if ("following".equals(type)) {
            list = networkService.getFollowingList(userId, pageSize, offset);
            total = networkService.getFollowingCount(userId);
        } else if("followers".equals(type)) {
            list = networkService.getFollowerList(userId, pageSize, offset);
            total = networkService.getFollowerCount(userId);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("total", total);
        response.put("list", list);
        return ResponseEntity.ok(response);
    }
}
