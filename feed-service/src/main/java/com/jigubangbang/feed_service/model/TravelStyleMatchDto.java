package com.jigubangbang.feed_service.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TravelStyleMatchDto {
    private String partnerTravelStyleId;
    private String partnerTravelStyleLabel;
    private boolean matchStatus;
    private String description;
    private String image;
}
