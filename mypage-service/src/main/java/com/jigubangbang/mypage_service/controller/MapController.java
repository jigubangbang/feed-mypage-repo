package com.jigubangbang.mypage_service.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jigubangbang.mypage_service.model.CityDto;
import com.jigubangbang.mypage_service.model.CountryDto;
import com.jigubangbang.mypage_service.model.CountryVisitDto;
import com.jigubangbang.mypage_service.model.CountryWishDto;
import com.jigubangbang.mypage_service.model.FeedPostDto;
import com.jigubangbang.mypage_service.service.MapService;
import com.jigubangbang.mypage_service.util.DateUtils;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/profile")
public class MapController {
    @Resource
    private MapService mapService;

    @GetMapping("/{userId}/countries/search")
    public ResponseEntity<Map<String, Object>> searchCountries(@RequestParam(required=false) String keyword, @PathVariable String userId) {
        List<CountryDto> countries = mapService.getCountryList(userId, keyword);

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

    @PostMapping("/{userId}/countries/visited")
    public ResponseEntity<Map<String, Object>> addVisitCountry(@RequestBody CountryVisitDto dto) {
        boolean success = mapService.addVisitCountry(dto);
        if (success) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Added country successfully");
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Map.of("error", "Failed to add country"));
    }

    @DeleteMapping("/{userId}/countries/visited/{countryId}")
    public ResponseEntity<Map<String, Object>> removeVisitCountry(@PathVariable String userId, @PathVariable String countryId) {
        boolean success = mapService.removeVisitCountry(userId, countryId);
        if (success) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Removed country successfully");
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Map.of("error", "Failed to remove country"));
    }

    @PostMapping("/{userId}/countries/wishlist")
    public ResponseEntity<Map<String, Object>> addWishlistCountry(@RequestBody CountryWishDto dto) {
        boolean success = mapService.addWishlistCountry(dto);
        if (success) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Added country successfully");
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Map.of("error", "Failed to add country"));
    }

    @DeleteMapping("/{userId}/countries/wishlist/{countryId}")
    public ResponseEntity<Map<String, Object>> removeWishlistCountry(@PathVariable String userId, @PathVariable String countryId) {
        boolean success = mapService.removeWishlistCountry(userId, countryId);
        if (success) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Removed country successfully");
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Map.of("error", "Removed to remove country"));
    }

    @GetMapping("/countries/{countryId}/cities")
    public ResponseEntity<Map<String, Object>> getCityList(@PathVariable String countryId) {
        List<CityDto> cities = mapService.getCityList(countryId);
        Map<String, Object> response = new HashMap<>();
        response.put("totalItems", cities.size());
        response.put("countryId", countryId);
        response.put("cities", cities);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}/countries/{countryId}")
    public ResponseEntity<Map<String, Object>> getCountryFeed(@RequestHeader("User-Id") String userId, @PathVariable String countryId) {
        List<FeedPostDto> posts = mapService.getCountryFeed(userId, countryId);
        Map<String, Object> response = new HashMap<>();
        response.put("totalItems", posts.size());
        response.put("countryId", countryId);
        response.put("posts", posts);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{userId}/map/settings")
    public ResponseEntity<Map<String, Object>> changeMapColor(@PathVariable String userId, @RequestParam("color") String color) {
        boolean success = mapService.changeMapColor(userId, color);
        if (success) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Color updated successfully");
            response.put("color", color);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Map.of("error",  "Failed to update map color"));
    }
}
