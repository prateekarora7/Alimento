package com.alimento.prototype.services;

import com.alimento.prototype.dtos.CommentDTO;
import com.alimento.prototype.entities.Comment;

public interface CommentService {

    void saveComment(CommentDTO commentDTO);

    boolean deleteComment(int commentId);
}
