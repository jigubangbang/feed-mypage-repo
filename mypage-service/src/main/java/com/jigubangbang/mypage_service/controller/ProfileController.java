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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jigubangbang.mypage_service.model.CountryDto;
import com.jigubangbang.mypage_service.model.LanguageDto;
import com.jigubangbang.mypage_service.model.ProfileDto;
import com.jigubangbang.mypage_service.service.ProfileService;

import jakarta.annotation.Resource;
import com.jigubangbang.mypage_service.util.DateUtils;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Resource
    private ProfileService profileService;

    // TODO : replace all sessionUserId ("bbb") w/ real logged in user
    @GetMapping("/{userId}")
    public ProfileDto getProfile(@PathVariable String userId) {
        String sessionUserId = "bbb"; // TODO: replace w/ session user
        ProfileDto profileDto = profileService.getProfileDto(userId);
        
        boolean followStatus = profileService.getFollowStatus(sessionUserId, userId);
        profileDto.setFollowStatus(followStatus);
        
        profileDto.setFormattedDate(DateUtils.formatToKoreanDate(profileDto.getCreatedAt()));
        return profileDto;
    }

    @PutMapping("/{userId}/travel-status")
    public ResponseEntity<Map<String, Object>> updateTravelStatus(@PathVariable String userId,
            @RequestParam("status") String travelStatus) {
        boolean success = profileService.updateTravelStatus(userId, travelStatus);

        if (success) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Travel status updated successfully");
            return ResponseEntity.ok(response); // 200 ok
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Map.of("error", "Failed to update travel status"));
    }

    @PostMapping("/{userId}/network")
    public ResponseEntity<Map<String, Object>> followUser(@PathVariable String userId) {
        String sessionUserId = "bbb"; // TODO: replace w/ session user
        boolean success = profileService.followUser(sessionUserId, userId);

        if (success) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Followed successfully");
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Map.of("error", "Failed to follow user"));
    }

    @DeleteMapping("/{userId}/network")
    public ResponseEntity<Map<String, Object>> unfollowUser(@PathVariable String userId) {
        String sessionUserId = "bbb"; // TODO: replace w/ session user
        boolean success = profileService.unfollowUser(sessionUserId, userId);

        if (success) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Unfollowed successfully");
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Map.of("error", "Failed to unfollow user"));
    }

    @GetMapping("/{userId}/favorites")
    public ResponseEntity<Map<String, Object>> getTopCountries(@PathVariable String userId) {
        List<CountryDto> countryList = profileService.getTopCountries(userId);
        Map<String, Object> response = new HashMap<>();
        response.put("totalItems", countryList.size());
        response.put("countries", countryList);
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}/languages")
    public ResponseEntity<Map<String, Object>> getUserLanguages(@PathVariable String userId) {
        List<LanguageDto> languageList = profileService.getUserLanguages(userId);
        Map<String, Object> response = new HashMap<>();
        response.put("totalItems", languageList.size());
        response.put("languages", languageList);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{userId}/languages")
    public ResponseEntity<Map<String, Object>> addLanguage(@PathVariable String userId,
            @RequestBody Map<String, Object> request) {
        int languageId = (int) request.get("languageId");
        String proficiency = (String) request.get("proficiency");
        
        boolean success = profileService.addLanguage(userId, languageId, proficiency);

        if (success) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Language added successfully");
            return ResponseEntity.ok(response); 
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Map.of("error", "Failed to add language"));
    }

    @DeleteMapping("/{userId}/languages/{languageId}")
    public ResponseEntity<Map<String, Object>> removeLanguage(@PathVariable int languageId) {
        boolean success = profileService.removeLanguage(languageId);

        if (success) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Removed language successfully");
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Map.of("error", "Failed to remove language"));
    }
}
