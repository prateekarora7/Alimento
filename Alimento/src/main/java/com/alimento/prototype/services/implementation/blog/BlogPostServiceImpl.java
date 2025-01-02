package com.alimento.prototype.services.implementation.blog;

import com.alimento.prototype.dtos.blog.BlogPostRequest;
import com.alimento.prototype.dtos.blog.ContentBlockRequest;
import com.alimento.prototype.entities.blog.BlogPost;
import com.alimento.prototype.entities.blog.ContentBlock;
import com.alimento.prototype.repositories.blog.BlogPostRepository;
import com.alimento.prototype.services.blog.BlogPostService;
import com.alimento.prototype.services.blog.ContentBlockService;
import com.alimento.prototype.utils.SlugUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BlogPostServiceImpl implements BlogPostService {

    @Autowired
    private final BlogPostRepository blogPostRepository;

    @Autowired
    private final ContentBlockService contentBlockService;

    @Autowired
    private SlugUtil slugUtil;

    public BlogPostServiceImpl(BlogPostRepository blogPostRepository, ContentBlockService contentBlockService) {
        this.blogPostRepository = blogPostRepository;
        this.contentBlockService = contentBlockService;
    }

    @Transactional
    @Override
    public void saveBlogPostDTO(BlogPostRequest blogPostRequest) {

        //formatting the slug to lowercase and eliminating free space to make it url friendly
        String formattedSlug = slugUtil.toSlug(blogPostRequest.getTitle());

        blogPostRepository.saveBlogPostSkeleton(blogPostRequest.getTitle(), blogPostRequest.getAuthorName(), LocalDateTime.now(), slugUtil.generateUniqueSlug(blogPostRepository.matchingBaseSlugs(formattedSlug+"%"), formattedSlug));

        String finalFormattedSlug = formattedSlug; // variables used inside lambdas must be final as they can be executed by diffrent threads

        blogPostRequest.getContentBlockList().stream().forEach(e -> e.setSlug(finalFormattedSlug)); //This sets slug attribute of each block as content block to the generated slug

        contentBlockService.saveContentBlocks(blogPostRequest.getContentBlockList());

    }

    @Override
    public BlogPost getBlogPostbyId(long blogId) {
        return blogPostRepository.getBlogPostByBlogId(blogId);
    }

    @Override
    public BlogPost getBlogPostBySlug(String slug) {
        return blogPostRepository.getBlogPostBySlug(slug);
    }

    @Override
    public List<String> matchingBaseSlugs(String slug){
        return blogPostRepository.matchingBaseSlugs(slug);
    }

    @Override
    public Integer existsBySlug(String slug) {
        return blogPostRepository.existsBySlug(slug);
    }

}
