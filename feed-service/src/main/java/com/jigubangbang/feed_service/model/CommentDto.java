package com.jigubangbang.feed_service.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
    private int id;
    private int feedId;
    private String userId;
    private String nickname;
    private String profileImage;
    private Integer parentCommentId;
    private String content;
    private LocalDateTime createdAt;
    private String blindStatus;

    private boolean likeStatus;
    private int likeCount;
    private int replyCount;
}