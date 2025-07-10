package com.jigubangbang.feed_service.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.jigubangbang.feed_service.model.CommentDto;

@Mapper
public interface CommentMapper {
    public CommentDto getCommentById(int commentId);
    public List<CommentDto> getComments(Map<String, Object> map);
    public List<CommentDto> getReplies(Map<String, Object> map);
    public int addComment(CommentDto dto);
    public int deleteComment(int id);
    public int likeComment(Map<String, Object> map);
    public int unlikeComment(Map<String, Object> map);
}
