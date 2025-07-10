package com.jigubangbang.feed_service.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.jigubangbang.feed_service.model.FeedDto;
import com.jigubangbang.feed_service.model.FeedImageDto;
import com.jigubangbang.feed_service.model.PostDto;

@Mapper
public interface FeedMapper {
    public List<FeedDto> getUserPosts(Map<String, Object> map);
    public PostDto getPostDetail(int id);
    public List<FeedImageDto> getPostImages(int feedId);
    public int getPostLikeStatus(Map<String, Object> map);
    public int getPostBookmarkStatus(Map<String, Object> map);
    public int addPost(PostDto dto);
    public int addVisitCountry(PostDto dto);
    public int addPostImage(FeedImageDto dto);
    public int deletePost(int id);
    public int updatePost(PostDto dto);
    public int updatePublicStatus(Map<String, Object> map);
    public int likePost(Map<String, Object> map);
    public int unlikePost(Map<String, Object> map);
    public int bookmarkPost(Map<String, Object> map);
    public int removeBookmarkPost(Map<String, Object> map);
}
