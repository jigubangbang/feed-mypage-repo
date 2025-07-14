package com.jigubangbang.mypage_service.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileDto {
    private String userId;
    private String nickname;
    private String profileImage;
    private Timestamp createdAt;
    private boolean isPremium;
    private String nationality;
    private String bio;
    private String travelStyleId;
    private String travelStatus;
    private String mapColor;
    private int xp;
    private int level;

    private String nationalityName;
    private String travelStyleName;
    private int followerCount;
    private int followingCount;
    private int countryVisitCount;
    private String formattedDate;
    private boolean followStatus;

    private BadgeDto badge;
}