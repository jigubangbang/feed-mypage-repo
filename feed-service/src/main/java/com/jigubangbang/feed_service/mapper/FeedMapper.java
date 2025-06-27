package com.jigubangbang.feed_service.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.jigubangbang.feed_service.model.PostBookmarkDto;
import com.jigubangbang.feed_service.model.PostDto;
import com.jigubangbang.feed_service.model.PostLikeDto;

@Mapper
public interface FeedMapper {
    public List<PostDto> getUserPosts(String userId);
    public PostDto getPostDetail(int id);
    public int getPostLikeStatus(Map<String, Object> map);
    public int getPostBookmarkStatus(Map<String, Object> map);
    public int addPost(PostDto dto);
    public int deletePost(int id);
    public int likePost(PostLikeDto dto);
    public int unlikePost(Map<String, Object> map);
    public int bookmarkPost(PostBookmarkDto dto);
    public int removeBookmarkPost(Map<String, Object> map);
}
