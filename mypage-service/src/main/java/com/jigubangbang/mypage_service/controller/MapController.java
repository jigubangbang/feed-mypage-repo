package com.jigubangbang.mypage_service.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jigubangbang.mypage_service.model.CountryDto;
import com.jigubangbang.mypage_service.model.CountryVisitDto;
import com.jigubangbang.mypage_service.service.MapService;
import com.jigubangbang.mypage_service.util.DateUtils;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/profile")
public class MapController {
    @Resource
    private MapService mapService;

    @GetMapping("/countries/search")
    public ResponseEntity<Map<String, Object>> searchCountries(@RequestParam(required=false) String keyword) {
        List<CountryDto> countries = mapService.getCountryList(keyword);

        Map<String, Object> response = new HashMap<>();
        response.put("totalItems", countries.size());
        response.put("countries", countries);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}/countries/visited")
    public ResponseEntity<Map<String, Object>> getVisitedCountries(@PathVariable String userId, @RequestParam(required=false) String continent) {
        List<CountryVisitDto> countries = mapService.getVisitedCountries(userId, continent);

        for (CountryVisitDto dto : countries) {
            dto.setFormattedStartDate(DateUtils.formatToKoreanDate(dto.getStartDate()));
            dto.setFormattedEndDate(DateUtils.formatToKoreanDate(dto.getEndDate()));
        }

        Map<String, Object> response = new HashMap<>();
        response.put("totalItems", countries.size());
        response.put("countries", countries);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}/countries/wishlist")
    public ResponseEntity<Map<String, Object>> getWishlistCountries(@PathVariable String userId) {
        List<CountryVisitDto> countries = mapService.getWishlistCountries(userId);

        for (CountryVisitDto dto : countries) {
            dto.setFormattedStartDate(DateUtils.formatToKoreanDate(dto.getStartDate()));
            dto.setFormattedEndDate(DateUtils.formatToKoreanDate(dto.getEndDate()));
        }

        Map<String, Object> response = new HashMap<>();
        response.put("totalItems", countries.size());
        response.put("countries", countries);

        return ResponseEntity.ok(response);
    }
}
