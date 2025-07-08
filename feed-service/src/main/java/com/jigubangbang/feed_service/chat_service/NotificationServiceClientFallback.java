package com.jigubangbang.feed_service.chat_service;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.jigubangbang.feed_service.model.chat_service.FeedNotificationRequestDto;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class NotificationServiceClientFallback implements NotificationServiceClient {
    
    @Override
    public ResponseEntity<Map<String, Object>> createFollowNotification(@RequestBody FeedNotificationRequestDto request) {
        log.warn("[Fallback] 팔로우 알림 전송 실패 - 메인 기능은 정상 처리됨");
        return ResponseEntity.ok(Map.of("success", false, "fallback", true));
    }
    
    @Override
    public ResponseEntity<Map<String, Object>> createFeedLikeNotification(@RequestBody FeedNotificationRequestDto request) {
        log.warn("[Fallback] 피드 좋아요 알림 전송 실패 - 메인 기능은 정상 처리됨");
        return ResponseEntity.ok(Map.of("success", false, "fallback", true));
    }

    @Override
    public ResponseEntity<Map<String, Object>> createFeedCommentNotification(@RequestBody FeedNotificationRequestDto request) {
        log.warn("[Fallback] 피드 댓글 알림 전송 실패 - 메인 기능은 정상 처리됨");
        return ResponseEntity.ok(Map.of("success", false, "fallback", true));
    }

}
