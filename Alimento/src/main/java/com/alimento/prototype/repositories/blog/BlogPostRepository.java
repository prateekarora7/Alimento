package com.alimento.prototype.repositories.blog;

import com.alimento.prototype.entities.blogs.BlogPost;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO blog_post (title, author_name, created_at, tags) " +
            "VALUES (:title, :authorName, :createdAt, :tags)", nativeQuery = true)
    void saveBlogPostDTO(@Param("title") String title, @Param("authorName") String authorName, @Param("createdAt") LocalDateTime createdAt, @Param("tags") String tags);


}
