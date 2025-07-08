package com.jigubangbang.feed_service.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jigubangbang.feed_service.mapper.CommentMapper;
import com.jigubangbang.feed_service.model.CommentDto;
import com.jigubangbang.feed_service.model.CommentLikeDto;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    public List<CommentDto> getComments(String userId, int feedId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("feedId", feedId);
        return commentMapper.getComments(map);
    }

    public boolean addComment(CommentDto dto) {
        return commentMapper.addComment(dto) > 0;
    }

    public boolean deleteComment(int id) {
        return commentMapper.deleteComment(id) > 0;
    }

    public boolean likeComment(CommentLikeDto dto) {
        return commentMapper.likeComment(dto) > 0;
    }

    public boolean unlikeComment(String userId, int commentId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("commentId", commentId);
        return commentMapper.unlikeComment(map) > 0;
    }

}
