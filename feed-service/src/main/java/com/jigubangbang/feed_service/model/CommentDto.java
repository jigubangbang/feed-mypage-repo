package com.jigubangbang.feed_service.model;

import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonFormat;

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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private Timestamp createdAt;

    private String blindStatus;

    private boolean likeStatus;
    private int likeCount;
    private int replyCount;
}