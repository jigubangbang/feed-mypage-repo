package com.jigubangbang.feed_service.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchUserDto {
    private String userId;
    private String nickname;
    private boolean isPremium;
    private String profileImage;
    private int followingCount;
    private int followerCount;
    private int countryVisitCount;
}
