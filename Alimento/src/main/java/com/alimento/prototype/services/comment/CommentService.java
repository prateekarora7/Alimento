package com.alimento.prototype.services.comment;

import com.alimento.prototype.dtos.comment.CommentDTO;
import com.alimento.prototype.entities.comment.Comment;

import java.util.List;

public interface CommentService {

    void saveComment(CommentDTO commentDTO);

    Comment getCommentByCommentId(long commentId);

    List<Comment> getCommentsByBlogIdAndUsername(int blogId, String username);

    List<Comment> getCommentsByUserId(String userId);

    List<Comment> getCommentsByBlogId(int blogId);

    void deleteComment(long commentId);

    Comment updateComment(long commentId, String commentContent);
}
