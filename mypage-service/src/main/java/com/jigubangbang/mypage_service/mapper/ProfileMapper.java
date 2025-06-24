package com.jigubangbang.mypage_service.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.jigubangbang.mypage_service.model.CountryDto;
import com.jigubangbang.mypage_service.model.LanguageDto;
import com.jigubangbang.mypage_service.model.ProfileDto;

@Mapper
public interface ProfileMapper {
    public ProfileDto getProfile(String userId);
    public String getCountryName(String countryId);
    public String getTravelStyleName(String travelStyleId);
    public int updateTravelStatus(Map<String, Object> map);
   
    public boolean getFollowStatus(Map<String, Object> map);
    public List<String> getFollowers(String userId);
    public List<String> getFollowing(String userId);
    public int followUser(Map<String, Object> map);
    public int unfollowUser(Map<String, Object> map);

    public List<CountryDto> getTopCountries(String userId);
    public List<LanguageDto> getUserLanguages(String userId);
    public int addLanguage(Map<String, Object> map);
    public int removeLanguage(int id);
}
