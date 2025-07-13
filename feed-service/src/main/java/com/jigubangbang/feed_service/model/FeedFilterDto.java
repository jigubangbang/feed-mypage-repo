package com.jigubangbang.feed_service.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedFilterDto {
    private String countryId;
    private Integer cityId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String sort; // "recent" or "like"
    private Integer pageSize;
    private Integer offset;
}
