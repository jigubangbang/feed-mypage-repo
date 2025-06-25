package com.jigubangbang.mypage_service.model;

import java.sql.Timestamp;

import lombok.Setter;

import lombok.Getter;

@Getter
@Setter
public class CountryVisitDto {
    private int id;
    private String userId;
    private String countryId;
    private Timestamp startDate;
    private Timestamp endDate;

    private String formattedStartDate;
    private String formattedEndDate;
}
