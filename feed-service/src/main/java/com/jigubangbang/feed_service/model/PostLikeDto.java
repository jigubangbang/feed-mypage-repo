package com.jigubangbang.feed_service.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostLikeDto {
    private int id;
    private int feedId;
    private String userId;
    private Timestamp likedAt;
}
