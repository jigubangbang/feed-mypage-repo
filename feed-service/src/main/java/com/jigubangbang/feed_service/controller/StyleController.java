package com.jigubangbang.feed_service.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jigubangbang.feed_service.model.TravelStyleBasicDto;
import com.jigubangbang.feed_service.model.TravelStyleDto;
import com.jigubangbang.feed_service.model.TravelStyleMatchDto;
import com.jigubangbang.feed_service.service.StyleService;

import jakarta.annotation.Resource;

@RestController
@RequestMapping
public class StyleController {
    @Resource
    private StyleService styleService;

    @PutMapping("/style/{styleId}")
    public ResponseEntity<Map<String, Object>> updateUserStyle(@RequestHeader("User-Id") String userId, @PathVariable String styleId) {
        boolean success = styleService.updateUserStyle(userId, styleId);
        if (success) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Updated travel style successfully");
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Map.of("error", "Failed to update travel style"));
    }

    @GetMapping("/public/styles")
    public ResponseEntity<Map<String, Object>> getStyleList() {
        List<TravelStyleBasicDto> styles = styleService.getStyleList();
        return ResponseEntity.ok(Map.of("styles", styles));
    }

    @GetMapping("/public/styles/{styleId}")
    public ResponseEntity<Map<String, Object>> getStyleDetail(@PathVariable("styleId") String styleId) {
        TravelStyleDto style = styleService.getStyleDetail(styleId);
        List<TravelStyleMatchDto> compatibleStyles = styleService.getCompatibleStyles(styleId);
        List<TravelStyleMatchDto> incompatibleStyles = styleService.getIncompatibleStyles(styleId);
        Map<String, Object> response = new HashMap<>();
        response.put("style", style);
        response.put("compatibleStyles", compatibleStyles);
        response.put("incompatibleStyles", incompatibleStyles);
        return ResponseEntity.ok(response);
    }
}
