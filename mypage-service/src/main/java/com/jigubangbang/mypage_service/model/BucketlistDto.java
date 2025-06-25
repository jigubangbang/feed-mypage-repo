package com.jigubangbang.mypage_service.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BucketlistDto {
    private int id;
    private String userId;
    private String title; 
    private String description;
    private boolean completionStatus;
    private Timestamp createdAt;
    private Timestamp completedAt;
    private int displayOrder;

    private String formattedDate;
}
