package com.jigubangbang.mypage_service.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedPostDto {
    private int id;
    private String userId;
    private String countryId;
    private int cityId;
    private String title;
    private Timestamp startDate;
    private Timestamp endDate;
    private boolean publicStatus;
    private Timestamp createdAt;
    private String blindStatus;
}