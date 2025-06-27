package com.jigubangbang.feed_service.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.jigubangbang.feed_service.model.CommentDto;
import com.jigubangbang.feed_service.model.CommentLikeDto;

@Mapper
public interface CommentMapper {
    public List<CommentDto> getComments(Map<String, Object> map);
    public int addComment(CommentDto dto);
    public int deleteComment(int id);
    public int likeComment(CommentLikeDto dto);
    public int unlikeComment(Map<String, Object> map);
}
