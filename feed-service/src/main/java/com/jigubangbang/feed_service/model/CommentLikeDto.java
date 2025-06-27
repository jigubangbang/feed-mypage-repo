package com.jigubangbang.feed_service.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentLikeDto {
    private int id;
    private int commentId;
    private String userId;
    private Timestamp likedAt;
}