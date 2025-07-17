package com.jigubangbang.mypage_service.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jigubangbang.mypage_service.model.BucketlistDto;
import com.jigubangbang.mypage_service.service.BucketlistService;
import com.jigubangbang.mypage_service.util.DateUtils;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/profile")
public class BucketlistController {
    @Resource
    private BucketlistService bucketlistService;

    @GetMapping("/{userId}/bucketlist")
    public ResponseEntity<Map<String, Object>> getBucketlist(@PathVariable String userId, 
            @RequestParam(required=false) String status) {
        Boolean completionStatus = null;
        if (status != null && status.equalsIgnoreCase("completed")) {
            completionStatus = true;
        }
        if (status != null && status.equalsIgnoreCase("incomplete")) {
            completionStatus = false;
        }

        List<BucketlistDto> bucketlist = bucketlistService.getBucketlist(userId, completionStatus);

        for (BucketlistDto dto : bucketlist) {
            dto.setFormattedDate(DateUtils.formatToKoreanDate(dto.getCompletedAt()));
        }

        Map<String, Object> response = new HashMap<>();
        response.put("totalItems", bucketlist.size());
        response.put("completeItems", bucketlistService.getCompleteItemsCount(userId));
        response.put("incompleteItems", bucketlistService.getIncompleteItemsCount(userId));
        response.put("items", bucketlist);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{userId}/bucketlist")
    public ResponseEntity<Map<String, Object>> addBucketlist(@PathVariable String userId, 
            @RequestBody BucketlistDto bucketlistDto) {
        bucketlistDto.setUserId(userId);
        boolean success = bucketlistService.addBucketlist(bucketlistDto);
        if (success) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Bucketlist item added successfully");
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Map.of("error", "Failed to add item"));
    }

    @DeleteMapping("/{userId}/bucketlist/{goalId}")
    public ResponseEntity<Map<String, Object>> deleteBucketlist(@PathVariable int goalId) {
        boolean success = bucketlistService.deleteBucketlist(goalId);
        if (success) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Bucketlist item deleted successfully");
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Map.of("error", "Failed to delete item"));
    }

    @PutMapping("/{userId}/bucketlist/{goalId}/status")
    public ResponseEntity<Map<String, Object>> checkBucketlist(@PathVariable int goalId) {
        boolean success = bucketlistService.checkBucketlist(goalId);
        if (success) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Updated status successfully");
            Timestamp date = bucketlistService.getCompletedDate(goalId);
            response.put("completedAt", (DateUtils.formatToKoreanDate(date)));
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Map.of("error", "Failed to (un)check item"));
    }

    @PutMapping("/{userId}/bucketlist/reorder")
    public ResponseEntity<Map<String, Object>> updateDisplayOrder(@RequestBody List<BucketlistDto> orderList) {
        bucketlistService.updateDisplayOrder(orderList);
        return ResponseEntity.ok().build();
    }
}
