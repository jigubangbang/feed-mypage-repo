package com.jigubangbang.mypage_service.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.jigubangbang.mypage_service.model.CountryDto;
import com.jigubangbang.mypage_service.model.CountryVisitDto;

@Mapper
public interface MapMapper {
    public List<CountryDto> getCountryList(String keyword);
    public List<CountryVisitDto> getVisitedCountries(Map<String, Object> map);
    public List<CountryVisitDto> getWishlistCountries(String userId);
    public List<Map<String, Object>> getVisitedCountPerContinent(String userId);
    public List<Map<String, Object>> getTotalCountPerContinent();
    public int getTotalCountriesCount();
    public int getUserVisitedCount(String userId);
    public double getUserVisitPercentile(String userId);
}
