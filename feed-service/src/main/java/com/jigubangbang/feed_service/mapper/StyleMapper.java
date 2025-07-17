package com.jigubangbang.feed_service.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jigubangbang.feed_service.model.TravelStyleBasicDto;
import com.jigubangbang.feed_service.model.TravelStyleDto;
import com.jigubangbang.feed_service.model.TravelStyleMatchDto;

@Mapper
public interface StyleMapper {
    public int updateUserStyle(Map<String, Object> map);
    public List<TravelStyleBasicDto> getStyleList();
    public TravelStyleDto getStyleDetail(@Param("id") String id);
    public List<TravelStyleMatchDto> getCompatibleStyles(@Param("travelStyleId") String travelStyleId);
    public List<TravelStyleMatchDto> getIncompatibleStyles(@Param("travelStyleId") String travelStyleId);
}
