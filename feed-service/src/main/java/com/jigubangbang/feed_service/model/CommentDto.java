package com.jigubangbang.feed_service.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
    private int id;
    private int feedId;
    private String userId;
    private int parentCommentId;
    private String content;
    private Timestamp createdAt;
    private String blindStatus;

    private boolean likeStatus;
}