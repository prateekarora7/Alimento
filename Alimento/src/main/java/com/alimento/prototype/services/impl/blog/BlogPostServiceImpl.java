package com.alimento.prototype.services.impl.blog;

import com.alimento.prototype.dtos.blog.BlogPostDTO;
import com.alimento.prototype.entities.blogs.BlogPost;
import com.alimento.prototype.entities.blogs.ContentBlock;
import com.alimento.prototype.repositories.blog.BlogPostRepository;
import com.alimento.prototype.repositories.blog.ContentBlockRepository;
import com.alimento.prototype.services.blog.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BlogPostServiceImpl implements BlogPostService {

    @Autowired
    private final BlogPostRepository blogPostRepository;

    @Autowired
    private final ContentBlockRepository contentBlockRepository;

    public BlogPostServiceImpl(BlogPostRepository blogPostRepository, ContentBlockRepository contentBlockRepository) {
        this.blogPostRepository = blogPostRepository;
        this.contentBlockRepository = contentBlockRepository;
    }

    @Override
    public void saveBlogPostDTO(BlogPostDTO blogPostDTO) {

        String formattedTags = String.join(", ", blogPostDTO.getTags());

        //Building the blog post without the content blocks and using the blog post DTO
        BlogPost blogPost = BlogPost.builder()
                .title(blogPostDTO.getTitle())
                .authorName(blogPostDTO.getAuthorName())
                .createdAt(LocalDateTime.now())
                .tags(formattedTags)
                .blocks(blogPostDTO.getContentBlocks())
                .build();

        //Here I am using .save method that comes from JPA Repository, Using native queries would not be efficient as they will
        blogPostRepository.save(blogPost);
    }

    @Override
    public BlogPost getBlogPostbyId(long blogId) {
        return blogPostRepository.getBlogPostByBlogId(blogId);
    }

}
