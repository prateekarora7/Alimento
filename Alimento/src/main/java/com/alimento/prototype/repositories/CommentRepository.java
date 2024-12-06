package com.alimento.prototype.repositories;

import com.alimento.prototype.entities.Comment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    //Abstract method to save a newly posted comment
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO comment (comment_content, blog_id, user_id) VALUES (:commentContent, :blogId, :userId)", nativeQuery = true)
    void saveComment(@Param("commentContent") String commentContent, @Param("blogId") int blogId, @Param("userId") String userId);

    //Abstract method to delete the comment using comment id
    @Query(value = "DELETE FROM comment WHERE id = :commentId", nativeQuery = true)
    void deleteComment(@Param("commentId") int commentId);

    //Abstract method to get comment by Id
    @Query(value = "SELECT * FROM comment WHERE comment_id = :commentId", nativeQuery = true)
    Comment getCommentById(@Param("commentId") int commentId);
}
