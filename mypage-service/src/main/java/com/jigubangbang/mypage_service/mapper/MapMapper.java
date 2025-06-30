package com.jigubangbang.mypage_service.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.jigubangbang.mypage_service.model.CityDto;
import com.jigubangbang.mypage_service.model.CountryDto;
import com.jigubangbang.mypage_service.model.CountryVisitDto;
import com.jigubangbang.mypage_service.model.CountryWishDto;
import com.jigubangbang.mypage_service.model.FeedPostDto;

@Mapper
public interface MapMapper {
    public List<CountryDto> getCountryList(Map<String, Object> map);
    public List<CountryVisitDto> getVisitedCountries(Map<String, Object> map);
    public List<CountryVisitDto> getWishlistCountries(String userId);
    public List<Map<String, Object>> getVisitedCountPerContinent(String userId);
    public List<Map<String, Object>> getTotalCountPerContinent();
    public int getTotalCountriesCount();
    public int getUserVisitedCount(String userId);
    public double getUserVisitPercentile(String userId);
    public int addVisitCountry(CountryVisitDto dto);
    public int removeVisitCountry(Map<String, Object> map);
    public int addWishlistCountry(CountryWishDto dto);
    public int removeWishlistCountry(Map<String, Object> map);
    public List<CityDto> getCityList(String countryId);
    public List<FeedPostDto> getCountryFeed(Map<String, Object> map);
}
