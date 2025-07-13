package com.jigubangbang.feed_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jigubangbang.feed_service.mapper.CountryMapper;
import com.jigubangbang.feed_service.model.CityDto;
import com.jigubangbang.feed_service.model.CountryDto;

@Service
public class CountryService {
    @Autowired
    private CountryMapper countryMapper;

    public List<CountryDto> getAllCountries() {
        return countryMapper.getAllCountries();
    }

    public List<CityDto> getCityByCountry(String countryId) {
        return countryMapper.getCityByCountry(countryId);
    }
}
