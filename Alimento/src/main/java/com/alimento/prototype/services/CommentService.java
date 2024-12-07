package com.alimento.prototype.services;

import com.alimento.prototype.dtos.CommentDTO;
import com.alimento.prototype.entities.Comment;

import java.util.List;

public interface CommentService {

    void saveComment(CommentDTO commentDTO);

    boolean deleteComment(int commentId);

    List<Comment> getCommentsByUserId(String userId);

    List<Comment> getCommentsByBlogId(int blogId);
}
