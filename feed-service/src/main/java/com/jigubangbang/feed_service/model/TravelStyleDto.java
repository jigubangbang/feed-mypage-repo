package com.jigubangbang.feed_service.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TravelStyleDto {
    private String id;
    private String label;
    private String description;
    private String keyword;
    private String travelPreference;
    private String travelRoutine;
    private String foodPreference;
    private String snsStyle;
    private String packingStyle;
    private String image;
}
