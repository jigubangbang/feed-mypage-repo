package com.jigubangbang.feed_service.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String userId;
    private String nickname;
    private boolean isPremium;
    private String profileImage;
    private String bio;
    private String nationality;
    private String travelStyleId;

    private String travelStyleLabel;
}
