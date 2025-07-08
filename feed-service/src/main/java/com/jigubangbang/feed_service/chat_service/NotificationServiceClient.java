package com.jigubangbang.feed_service.chat_service;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jigubangbang.feed_service.model.chat_service.FeedNotificationRequestDto;

@FeignClient( name="chat-service", configuration = NotificationServiceClientConfig.class, fallback = NotificationServiceClientFallback.class)
public interface NotificationServiceClient {

    // 팔로우 알림
    @PostMapping("/feed/follow")
    public ResponseEntity<Map<String, Object>> createFollowNotification(@RequestBody FeedNotificationRequestDto request);
    
    // 피드 좋아요 알림
    @PostMapping("/feed/like")
    public ResponseEntity<Map<String, Object>> createFeedLikeNotification(@RequestBody FeedNotificationRequestDto request);

    // 피드 댓글 알림
    @PostMapping("/feed/comment")
    public ResponseEntity<Map<String, Object>> createFeedCommentNotification(@RequestBody FeedNotificationRequestDto request);

    // 피드 게시글 알림 (승현 원할 시)
}