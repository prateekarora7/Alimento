package com.alimento.prototype.repositories;

import com.alimento.prototype.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

interface CommentRepository extends JpaRepository {

    void saveComment(Comment comment);

}
