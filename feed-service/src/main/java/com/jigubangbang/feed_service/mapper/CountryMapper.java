package com.jigubangbang.feed_service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jigubangbang.feed_service.model.CityDto;
import com.jigubangbang.feed_service.model.CountryDto;

@Mapper
public interface CountryMapper {
    public List<CountryDto> getAllCountries();
    public List<CityDto> getCityByCountry(String countryId);
    public String getCountryNameById(String id);
    public String getCityNameById(int id);
    public List<CountryDto> getRankedCountries(int limit);
}
