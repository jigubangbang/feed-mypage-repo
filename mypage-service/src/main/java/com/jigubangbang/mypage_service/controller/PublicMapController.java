package com.jigubangbang.mypage_service.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jigubangbang.mypage_service.model.CountryVisitBasicDto;
import com.jigubangbang.mypage_service.service.MapService;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("public")
public class PublicMapController {
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

    @GetMapping("/{userId}/map")
    public ResponseEntity<Map<String, Object>> getMapInfo(@PathVariable("userId") String userId) {
        String mapColor = mapService.getMapInfo(userId);
        List<CountryVisitBasicDto> countries = mapService.getBasicVisitedCountries(userId);
        Map<String, Object> response = new HashMap<>();
        response.put("mapColor", mapColor);
        response.put("countries", countries);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}/map/settings")
    public ResponseEntity<Map<String, Object>> getMapColor(@PathVariable String userId) {
        String mapColor = mapService.getMapInfo(userId);
        return ResponseEntity.ok(Map.of("mapColor", mapColor));
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
}
