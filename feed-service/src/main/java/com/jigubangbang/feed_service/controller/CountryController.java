package com.jigubangbang.feed_service.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jigubangbang.feed_service.model.CityDto;
import com.jigubangbang.feed_service.model.CountryDto;
import com.jigubangbang.feed_service.service.CountryService;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/public")
public class CountryController {
    @Resource
    private CountryService countryService;

    @GetMapping("/countries")
    public ResponseEntity<Map<String, Object>> getAllCountries() {
        List<CountryDto> countries = countryService.getAllCountries();
        return ResponseEntity.ok(Map.of("countries", countries));
    }

    @GetMapping("/countries/{countryId}/cities")
    public ResponseEntity<Map<String, Object>> getCityByCountry(@PathVariable("countryId") String countryId) {
        List<CityDto> cities = countryService.getCityByCountry(countryId);
        return ResponseEntity.ok(Map.of("cities", cities));
    }

    @GetMapping("/countries/top")
    public ResponseEntity<Map<String, Object>> getRankedCountries(@RequestParam(defaultValue = "5") int limit) {
        List<CountryDto> countries = countryService.getRankedCountries(limit);
        return ResponseEntity.ok(Map.of("countries", countries));
    }
}
