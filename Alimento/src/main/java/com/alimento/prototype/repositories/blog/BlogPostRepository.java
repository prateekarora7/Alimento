package com.alimento.prototype.repositories.blog;

import com.alimento.prototype.entities.blog.BlogPost;
import com.alimento.prototype.entities.blog.Tag;
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
    @Query(value = "INSERT INTO blog_post (title, author_name, created_at, slug) " +
            "VALUES (:title, :authorName, :createdAt, :slug)", nativeQuery = true)
    void saveBlogPostSkeleton(@Param("title") String title, @Param("authorName") String authorName, @Param("createdAt") LocalDateTime createdAt, @Param("slug") String slug);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO blog_post (tags) VALUES (:tags)", nativeQuery = true)
    void saveTags(@Param("tags") List<Tag> tags);


    @Query(value = "SELECT * FROM blog_post WHERE blog_id = :blogId", nativeQuery = true)
    BlogPost getBlogPostByBlogId(@Param("blogId") long blogId);

    @Query(value = "SELECT * FROM blog_post WHERE slug = :slug", nativeQuery = true)
    BlogPost getBlogPostBySlug(@Param("slug") String slug);

    @Query(value = "SELECT slug FROM blog_post WHERE slug LIKE :slug", nativeQuery = true)
    List<String> matchingBaseSlugs(String slug);

//    @Query(value = "INSERT INTO blog_post (")
//    void saveTags(Tag tag);
//    @Query(value = "INSERT INTO blog_post WHERE slug = :slug ")
//    void saveContentBLocksToBlogPost();

    //List<BlogPost> getBlogPostsByTag(@Param("slug") String slug); // filters using single tag

    //List<BlogPost> getBlogPostsByTags(@Param("slug") String slug); // Filters using multiple tags

    //BlogPost updateBlogPostBySlug(String oldSlug, String newSlug);

    //void deleteBlogBySlug(String slug);



    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN TRUE ELSE FALSE END " +
            "FROM blog_post WHERE slug = :slug", nativeQuery = true)
    Integer existsBySlug(@Param("slug") String slug);

}
