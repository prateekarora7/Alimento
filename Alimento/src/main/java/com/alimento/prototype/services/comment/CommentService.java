package com.alimento.prototype.services.comment;

import com.alimento.prototype.dtos.comment.CommentDTO;
import com.alimento.prototype.entities.comment.Comment;

import java.util.List;

public interface CommentService {

    void saveComment(CommentDTO commentDTO);

    boolean deleteComment(int commentId);

    List<Comment> getCommentsByUserId(String userId);

    List<Comment> getCommentsByBlogId(int blogId);
}
