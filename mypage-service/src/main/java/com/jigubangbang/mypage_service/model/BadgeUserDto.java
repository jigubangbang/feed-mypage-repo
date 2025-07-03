package com.jigubangbang.mypage_service.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BadgeUserDto {
    private int id;
    private String korTitle;
    private String description;
    private String icon;
    private int difficulty;

    private Timestamp awardedAt;
    private boolean isDisplayed;
}
