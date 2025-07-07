package com.jigubangbang.feed_service.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedDto {
    private int id;
    private String photoUrl; 

    private String countryName;
    private String cityName;

    private int likeCount;
    private int commentCount;
}
