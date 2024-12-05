package com.alimento.prototype.repositories;

import com.alimento.prototype.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    //Abstract method to save a newly posted comment
    void saveComment(Comment comment);

    //Abstract method to delete the comment using comment id
    void deleteComment(int commentId);

    //Abstract method to get comment by Id
    Comment getCommentById(int commentId);
}
