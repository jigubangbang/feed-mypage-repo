package com.jigubangbang.mypage_service.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BadgeDto {
    private int id;
    private String icon;

    private boolean acquiredStatus;
}
