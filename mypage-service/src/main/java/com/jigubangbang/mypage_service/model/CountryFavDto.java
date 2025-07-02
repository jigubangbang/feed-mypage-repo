package com.jigubangbang.mypage_service.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountryFavDto {
    private int id;
    private String countryId;
    private String name;
    private int countryRank;
}
