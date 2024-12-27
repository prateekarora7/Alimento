package com.alimento.prototype.services.implementation.blog;

import com.alimento.prototype.dtos.blog.BlogPostDTO;
import com.alimento.prototype.entities.blogs.BlogPost;
import com.alimento.prototype.repositories.blog.BlogPostRepository;
import com.alimento.prototype.repositories.blog.ContentBlockRepository;
import com.alimento.prototype.services.blog.BlogPostService;
import com.alimento.prototype.utils.SlugUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BlogPostServiceImpl implements BlogPostService {

    @Autowired
    private final BlogPostRepository blogPostRepository;

    @Autowired
    private SlugUtil slugUtil;

    public BlogPostServiceImpl(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    @Override
    public void saveBlogPostDTO(BlogPostDTO blogPostDTO) {

        String formattedTags = String.join(", ", blogPostDTO.getTags());

        String formattedSlug = slugUtil.toSlug(blogPostDTO.getSlug());


        //Building the blog post without the content blocks and using the blog post DTO
        BlogPost blogPost = BlogPost.builder()
                .title(blogPostDTO.getTitle())
                .authorName(blogPostDTO.getAuthorName())
                .slug(formattedSlug)
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

    @Override
    public BlogPost getBlogPostBySlug(String slug) {
        return blogPostRepository.getBlogPostBySlug(slug);
    }

}
