package com.jigubangbang.feed_service.model;

import java.sql.Timestamp;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {
    private int id;
    private String userId;
    private String countryName;
    private String cityName;
    private String title;
    private Timestamp startDate;
    private Timestamp endDate;
    private boolean publicStatus;
    private Timestamp createdAt;

    private String nickname;
    private String profileImage;

    private String photoUrl;
    private boolean likeStatus;
    private boolean bookmarkStatus;
    private String formattedStartDate;
    private String formattedEndDate;

    private int likeCount;
    private int commentCount;

    private List<FeedImageDto> images;
}