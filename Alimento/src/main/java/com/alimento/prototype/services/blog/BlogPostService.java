package com.alimento.prototype.services.blog;

import com.alimento.prototype.dtos.blog.BlogPostDTO;
import com.alimento.prototype.entities.blogs.BlogPost;
import com.alimento.prototype.entities.blogs.ContentBlock;

import java.time.LocalDateTime;
import java.util.List;

public interface BlogPostService {

    void saveBlogPostDTO(BlogPostDTO blogPostDTO);

    BlogPost getBlogPostbyId(long blogId);

}
