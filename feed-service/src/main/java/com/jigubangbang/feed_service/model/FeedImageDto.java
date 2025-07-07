package com.jigubangbang.feed_service.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedImageDto {
    private int id;
    private int feedId;
    private String photoUrl; 
    private int displayOrder;
}
