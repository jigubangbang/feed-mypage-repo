package com.jigubangbang.mypage_service.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LanguageUserDto {
    private String userId;
    private int id;
    private int languageId;
    private String name;
    private String proficiency;
}
