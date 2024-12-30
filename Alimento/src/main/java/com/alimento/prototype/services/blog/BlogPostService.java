package com.alimento.prototype.services.blog;

import com.alimento.prototype.dtos.blog.BlogPostDTO;
import com.alimento.prototype.entities.blog.BlogPost;

public interface BlogPostService {

    void saveBlogPostDTO(BlogPostDTO blogPostDTO);

    BlogPost getBlogPostbyId(long blogId);

    BlogPost getBlogPostBySlug(String slug);
}
