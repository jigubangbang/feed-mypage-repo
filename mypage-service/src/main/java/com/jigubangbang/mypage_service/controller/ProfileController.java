package com.jigubangbang.mypage_service.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jigubangbang.mypage_service.chat_service.NotificationServiceClient;
import com.jigubangbang.mypage_service.model.BioRequestDto;
import com.jigubangbang.mypage_service.model.CountryFavDto;
import com.jigubangbang.mypage_service.model.LanguageDto;
import com.jigubangbang.mypage_service.model.LanguageUserDto;
import com.jigubangbang.mypage_service.model.ProfileDto;
import com.jigubangbang.mypage_service.model.chat_service.FeedNotificationRequestDto;
import com.jigubangbang.mypage_service.service.ProfileService;
import com.jigubangbang.mypage_service.service.S3Service;

import jakarta.annotation.Resource;
import com.jigubangbang.mypage_service.util.DateUtils;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Resource
    private ProfileService profileService;

    @Resource
    private S3Service s3Service;

    @Autowired
    private NotificationServiceClient notificationClient;

    @GetMapping("/{userId}")
    public ProfileDto getProfile(@PathVariable String userId, @RequestHeader("User-Id") String sessionUserId) {
        ProfileDto profileDto = profileService.getProfileDto(userId);
        
        boolean followStatus = profileService.getFollowStatus(sessionUserId, userId);
        profileDto.setFollowStatus(followStatus);
        
        profileDto.setFormattedDate(DateUtils.formatToKoreanDate(profileDto.getCreatedAt()));
        return profileDto;
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Map<String, Object>> uploadProfilePic(@RequestParam("file") MultipartFile file, @PathVariable String userId) {
        try {
            String s3Url = s3Service.uploadFile(file, "profile-pictures/");
            profileService.updateProfileImage(userId, s3Url);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Uploaded profile image successfully");
            response.put("profileImage", s3Url);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "Upload failed"));
        }
    }

    @PutMapping("/{userId}/bio")
    public ResponseEntity<Map<String, Object>> updateBio(@RequestBody BioRequestDto dto) {
        boolean success = profileService.updateBio(dto);
        if (success) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Bio updated successfully");
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Map.of("error", "Failed to update bio"));
    }

    @PutMapping("/{userId}/nationality")
    public ResponseEntity<Map<String, Object>> updateNationality(@RequestBody ProfileDto dto) {
        boolean success = profileService.updateNationality(dto);
        if (success) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Nationality updated successfully");
            String nationality = dto.getNationality();
            response.put("nationality", nationality);
            response.put("nationalityName", profileService.getCountryName(nationality));
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Map.of("error", "Failed to updated nationality"));
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
    public ResponseEntity<Map<String, Object>> followUser(@PathVariable String userId, @RequestHeader("User-Id") String sessionUserId) {
        boolean success = profileService.followUser(sessionUserId, userId);

        if (success) {
            try {
                ProfileDto dto = profileService.getProfileDto(sessionUserId);
                String profileImage = dto.getProfileImage();
                String nickname = dto.getNickname();
                FeedNotificationRequestDto request = FeedNotificationRequestDto.builder()
                    .authorId(userId)
                    .relatedUrl("/profile/" + sessionUserId)
                    .senderId(sessionUserId)
                    .nickname(nickname)
                    .senderProfileImage(profileImage)
                    .build();

                System.out.println("[ProfileController] 팔로우 알림 발송: " + sessionUserId + "->" + userId);
                notificationClient.createFollowNotification(request);
            } catch (Exception e) {
                System.out.println("[ProfileController] 알림 발송 실패: " + e.getMessage());
            }

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Followed successfully");
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Map.of("error", "Failed to follow user"));
    }

    @DeleteMapping("/{userId}/network")
    public ResponseEntity<Map<String, Object>> unfollowUser(@PathVariable String userId, @RequestHeader("User-Id") String sessionUserId) {
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
    public ResponseEntity<Map<String, Object>> getFavCountries(@PathVariable String userId) {
        List<CountryFavDto> countryList = profileService.getFavCountries(userId);
        Map<String, Object> response = new HashMap<>();
        response.put("totalItems", countryList.size());
        response.put("countries", countryList);
        
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{userId}/favorites")
    public ResponseEntity<Map<String, Object>> updateFavCountries(@PathVariable String userId, @RequestBody List<CountryFavDto> favCountries) {
        boolean success = profileService.updateFavCountries(userId, favCountries);
        if (success) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Updated list successfully");
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Map.of("error", "Failed to update list"));
    }

    @GetMapping("/{userId}/languages/search")
    public ResponseEntity<Map<String, Object>> getLanguageList(@PathVariable String userId, @RequestParam(required=false) String keyword) {
        List<LanguageDto> languageList = profileService.getLanguageList(userId, keyword);
        Map<String, Object> response = new HashMap<>();
        response.put("totalItems", languageList.size());
        response.put("languages", languageList);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}/languages")
    public ResponseEntity<Map<String, Object>> getUserLanguages(@PathVariable String userId) {
        List<LanguageUserDto> languageList = profileService.getUserLanguages(userId);
        Map<String, Object> response = new HashMap<>();
        response.put("totalItems", languageList.size());
        response.put("languages", languageList);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{userId}/languages")
    public ResponseEntity<Map<String, Object>> addLanguage(@PathVariable String userId, @RequestBody LanguageUserDto dto) {
        boolean success = profileService.addLanguage(dto);

        if (success) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Language added successfully");
            int languageId = dto.getLanguageId();
            int id = profileService.getIdByLanguageUser(userId, languageId);
            response.put("id", id);
            return ResponseEntity.ok(response); 
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Map.of("error", "Failed to add language"));
    }

    @DeleteMapping("/{userId}/languages/{id}")
    public ResponseEntity<Map<String, Object>> removeLanguage(@PathVariable int id) {
        boolean success = profileService.removeLanguage(id);

        if (success) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Removed language successfully");
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Map.of("error", "Failed to remove language"));
    }

    @PutMapping("/{userId}/languages/{id}")
    public ResponseEntity<Map<String, Object>> updateLanguage(@RequestBody LanguageUserDto dto) {
        boolean success = profileService.updateLanguage(dto);
        if (success) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Updated language successfully");
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Map.of("error", "Failed to update language"));
    }

    @GetMapping("/membership-status")
    public ResponseEntity<Map<String, Object>> isUserPremium(@RequestHeader("User-Id") String userId) {
        boolean isPremium = profileService.isUserPremium(userId);
        return ResponseEntity.ok(Map.of("status", isPremium));
    }
}
