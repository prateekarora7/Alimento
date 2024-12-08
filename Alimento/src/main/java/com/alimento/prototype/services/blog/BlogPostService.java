package com.alimento.prototype.services.blog;

import com.alimento.prototype.dtos.blog.BlogPostDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface BlogPostService {

    long saveBlogPostDTO(BlogPostDTO blogPostDTO);

}
