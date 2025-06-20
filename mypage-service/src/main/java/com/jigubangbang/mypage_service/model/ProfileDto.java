package com.jigubangbang.mypage_service.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileDto {
    private String userId;
    private String nickname;
    private String profileImage;
    private String createdAt;
    private String nationality;
    private String nationalityLabel;
    private String bio;
    private Character travelStyleId;
    private String travelStyleName;
    private String travelStatus;
    private String mapColor;
    private int xp;
    private int level;
    private int followerCount;
    private int followingCount;
    private int countryVisitCount;
}