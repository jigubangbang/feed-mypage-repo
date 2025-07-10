package com.jigubangbang.feed_service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jigubangbang.feed_service.model.FeedDto;
import com.jigubangbang.feed_service.model.HashtagDto;

@Mapper
public interface HashtagMapper {
    public HashtagDto findHashtagByName(String name);
    public int insertHashtag(HashtagDto dto);
    public int insertFeedHashtag(@Param("feedId") int feedId, @Param("hashtagId") int hashtagId);
    public int incrementHashtagCount(int id);
    public List<HashtagDto> getTrendingHashtags(@Param("limit") int limit);
    public List<FeedDto> getFeedByHashtag(@Param("tag") String tag, @Param("pageSize") int pageSize, @Param("offset") int offset);
}
