package com.jigubangbang.mypage_service.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jigubangbang.mypage_service.model.BadgeDto;
import com.jigubangbang.mypage_service.model.BadgeUserDto;
import com.jigubangbang.mypage_service.model.BioRequestDto;
import com.jigubangbang.mypage_service.model.CountryFavDto;
import com.jigubangbang.mypage_service.model.LanguageDto;
import com.jigubangbang.mypage_service.model.LanguageUserDto;
import com.jigubangbang.mypage_service.model.ProfileDto;

@Mapper
public interface ProfileMapper {
    public ProfileDto getProfile(String userId);
    public int updateProfileImage(Map<String, Object> map);
    public int updateBio(BioRequestDto dto);
    public int updateNationality(ProfileDto dto);
    public String getCountryName(String countryId);
    public String getTravelStyleName(String travelStyleId);
    public int updateTravelStatus(Map<String, Object> map);
   
    public boolean getFollowStatus(Map<String, Object> map);
    public List<String> getFollowers(String userId);
    public List<String> getFollowing(String userId);
    public int followUser(Map<String, Object> map);
    public int unfollowUser(Map<String, Object> map);

    public List<CountryFavDto> getFavCountries(String userId);
    public int deleteFavCountriesByUserId(String userId);
    public int insertFavCountries(@Param("userId") String userId, @Param("favCountries") List<CountryFavDto> favCountries);

    public List<LanguageDto> getLanguageList(Map<String, Object> map);
    public List<LanguageUserDto> getUserLanguages(String userId);
    public int addLanguage(LanguageUserDto dto);
    public int removeLanguage(int id);
    public int updateLanguage(LanguageUserDto dto);
    public int getIdByLanguageUser(Map<String, Object> map);
    public boolean isUserPremium(String userId);
}
