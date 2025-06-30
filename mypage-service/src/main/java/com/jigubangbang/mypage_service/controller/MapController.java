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
import org.springframework.web.bind.annotation.RequestBody;
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

    private static final Map<String, String> CONTINENT_KO_MAP = Map.of(
        "Asia", "아시아",
        "Europe", "유럽",
        "North America", "북아메리카",
        "South America", "남아메리카",
        "Africa", "아프리카",
        "Oceania", "오세아니아",
        "Antarctica", "남극"
    );

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

    @GetMapping("/{userId}/countries/stats")
    public ResponseEntity<Map<String, Object>> getUserStats(@PathVariable String userId) {
        int totalVisited = mapService.getUserVisitedCount(userId);
        int totalCountries = mapService.getTotalCountriesCount();
        double percentile = mapService.getUserVisitPercentile(userId);

        List<Map<String, Object>> totalPerContinent = mapService.getTotalCountPerContinent();
        List<Map<String, Object>> visitedPerContinent = mapService.getVisitedCountPerContinent(userId);

        Map<String, Map<String, Object>> stats = new HashMap<>();

        for (Map<String, Object> row : totalPerContinent) {
            String continent = (String) row.get("continent");
            int total = ((Number) row.getOrDefault("totalCount", 0)).intValue();

            stats.putIfAbsent(continent, new HashMap<String, Object>());
            stats.get(continent).put("total", total);
        }
        
        for (Map<String, Object> row : visitedPerContinent) {
            String continent = (String) row.get("continent");
            int visited = ((Number) row.getOrDefault("visitedCount", 0)).intValue();

            stats.putIfAbsent(continent, new HashMap<String, Object>());
            stats.get(continent).put("visited", visited);
        }

        for (String continent : stats.keySet()) {
            int visited = (int) stats.get(continent).getOrDefault("visited", 0);
            int total = (int) stats.get(continent).getOrDefault("total", 0);
            double percentage = total > 0 ? (((double) visited / total) * 100.0) : 0;
            percentage = Math.round(percentage * 10) / 10.0;
            stats.get(continent).put("percentage", percentage);

            String continent_ko = CONTINENT_KO_MAP.get(continent);
            stats.get(continent).put("continent_ko", continent_ko);
        }


        Map<String, Object> response = new HashMap<>();
        response.put("totalVisited", totalVisited);
        response.put("totalCountries", totalCountries);
        response.put("percentile", percentile);
        response.put("continents", stats);

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
    public ResponseEntity<Map<String, Object>> getCountryFeed(@PathVariable String userId, @PathVariable String countryId) {
        List<FeedPostDto> feedPosts = mapService.getCountryFeed(userId, countryId);
        Map<String, Object> response = new HashMap<>();
        response.put("totalItems", feedPosts.size());
        response.put("countryId", countryId);
        response.put("posts", feedPosts);

        return ResponseEntity.ok(response);
    }
}
