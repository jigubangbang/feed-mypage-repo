package com.jigubangbang.mypage_service.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String userId;
    private String nickname;
    private boolean isPremium;
    private String profileImage;

    private int followerCount;
    private int followingCount;
    private int countryVisitCount;
}
