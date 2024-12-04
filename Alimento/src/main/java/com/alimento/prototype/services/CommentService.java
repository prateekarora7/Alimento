package com.alimento.prototype.services;

import com.alimento.prototype.entities.Comment;

public interface CommentService {

    void saveComment(String comment, int blogId, int userId);

}
