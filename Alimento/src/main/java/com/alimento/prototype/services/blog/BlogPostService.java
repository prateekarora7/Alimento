package com.alimento.prototype.services.blog;

import com.alimento.prototype.dtos.blog.BlogPostRequest;
import com.alimento.prototype.entities.blog.BlogPost;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BlogPostService {

    void saveBlogPostDTO(BlogPostRequest blogPostDTO);

    BlogPost getBlogPostbyId(long blogId);

    BlogPost getBlogPostBySlug(String slug);

    List<String> matchingBaseSlugs(String slug);

    Integer existsBySlug(@Param("slug") String slug);
}
