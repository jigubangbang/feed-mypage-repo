package com.jigubangbang.mypage_service.chat_service;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jigubangbang.mypage_service.model.chat_service.FeedNotificationRequestDto;

@FeignClient( name="chat-service", configuration = NotificationServiceClientConfig.class, fallback = NotificationServiceClientFallback.class)
public interface NotificationServiceClient {

    // 팔로우 알림
    @PostMapping("/notifications/feed/follow")
    public ResponseEntity<Map<String, Object>> createFollowNotification(@RequestBody FeedNotificationRequestDto request);
    
}