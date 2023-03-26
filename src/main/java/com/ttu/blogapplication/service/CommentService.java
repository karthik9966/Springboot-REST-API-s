package com.ttu.blogapplication.service;

import com.ttu.blogapplication.payload.CommentDTO;

import java.util.List;

public interface CommentService {
    CommentDTO save(long postId, CommentDTO comment);

    CommentDTO getComment(long postId, long commentId);

    List<CommentDTO> getComments(int postId);

    CommentDTO updateComment(long postId, long commentId, CommentDTO commentDTO);

    void deleteById(long id, long commentId);
}
