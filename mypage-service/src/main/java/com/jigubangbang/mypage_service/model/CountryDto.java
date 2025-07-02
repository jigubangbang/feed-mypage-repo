package com.jigubangbang.mypage_service.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountryDto {
    private String id;
    private String name;
    private String continent;
    private boolean visitStatus;
    private boolean wishStatus;
    private boolean favStatus;
}
