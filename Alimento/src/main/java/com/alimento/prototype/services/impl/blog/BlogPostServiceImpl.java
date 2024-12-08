package com.alimento.prototype.services.impl.blog;

import com.alimento.prototype.dtos.blog.BlogPostDTO;
import com.alimento.prototype.entities.blogs.BlogPost;
import com.alimento.prototype.repositories.blog.BlogPostRepository;
import com.alimento.prototype.services.blog.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BlogPostServiceImpl implements BlogPostService {

    @Autowired
    private final BlogPostRepository blogPostRepository;

    public BlogPostServiceImpl(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    @Override
    public long saveBlogPostDTO(BlogPostDTO blogPostDTO) {

        //Building the blog post without the content blocks and using the blog post DTO
        BlogPost blogPost = BlogPost.builder()
                .title(blogPostDTO.getTitle())
                .authorName(blogPostDTO.getAuthorName())
                .createdAt(LocalDateTime.now())
                .tags(blogPostDTO.getTags())
                .build();

        blogPostRepository.saveBlogPostDTO(blogPost.getTitle(),blogPost.getAuthorName(), blogPost.getCreatedAt(), blogPost.getTags());   // saving the blogDTO in a blog to generate an Blog Id

        Long blogId = blogPost.getBlogId();

        //Checking if the blog id variable is populated or not and throwing exception accordingly
        if(blogId == null) throw new RuntimeException("Facing issues, Blog not saved!!!");

        return blogId;
    }

}
