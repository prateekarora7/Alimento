package com.alimento.prototype.services.implementation.blog;

import com.alimento.prototype.dtos.blog.BlogPostRequest;
import com.alimento.prototype.entities.blog.BlogPost;
import com.alimento.prototype.entities.blog.ContentBlock;
import com.alimento.prototype.entities.blog.Tag;
import com.alimento.prototype.mapper.ContentBlockRequestToContentBlock;
import com.alimento.prototype.repositories.blog.BlogPostRepository;
import com.alimento.prototype.repositories.blog.TagRepository;
import com.alimento.prototype.services.blog.BlogPostService;
import com.alimento.prototype.services.blog.ContentBlockService;
import com.alimento.prototype.utils.SlugUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BlogPostServiceImpl implements BlogPostService {

    @Autowired
    private final BlogPostRepository blogPostRepository;

    @Autowired
    private final ContentBlockService contentBlockService;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private SlugUtil slugUtil;


    public BlogPostServiceImpl(BlogPostRepository blogPostRepository, ContentBlockService contentBlockService) {
        this.blogPostRepository = blogPostRepository;
        this.contentBlockService = contentBlockService;
    }

    @Transactional
    @Override
    public void saveBlogPostDTO(BlogPostRequest blogPostRequest) {

//        //formatting the slug to lowercase and eliminating free space to make it url friendly
//        String formattedSlug = slugUtil.toSlug(blogPostRequest.getTitle());
//        String finalFormattedSlug = slugUtil.generateUniqueSlug(blogPostRepository.matchingBaseSlugs(formattedSlug+"%"), formattedSlug);
//        blogPostRepository.saveBlogPostSkeleton(blogPostRequest.getTitle(), blogPostRequest.getAuthorName(), LocalDateTime.now(), finalFormattedSlug);
//
//
//        blogPostRequest.getContentBlockList().stream().forEach(e -> e.setSlug(finalFormattedSlug)); //This sets slug attribute of each block as content block to the generated slug
//        contentBlockService.saveContentBlocks(blogPostRequest.getContentBlockList());
//
//        HashMap<String, Tag> tagMap = new HashMap<>();
//        BlogPost blog = getBlogPostBySlug(finalFormattedSlug);
//        blogPostRequest.getTags().stream().forEach(tag -> tagMap.put(tag.getTagName(), tag));
//        List<Tag> tags = new ArrayList<>();
//        for (Tag tag:tagMap.values()){
//            tags.add(tag);
//        }
//        blog.setTags(tags);
//        System.out.println(blog.getTags());
//        blogPostRepository.save(blog);

        //////////////////////////////////////////////////////
        String formattedSlug = slugUtil.toSlug(blogPostRequest.getTitle());  //formatting the slug to lowercase and eliminating free space to make it url friendly
        String finalFormattedSlug = slugUtil.generateUniqueSlug(blogPostRepository.matchingBaseSlugs(formattedSlug+"%"), formattedSlug);  //Generating unique slugs, it will add number with hyphen at the end of slug if slug already exists

        blogPostRequest.getBlocks().stream().forEach(e -> e.setSlug(finalFormattedSlug)); //saving
        List<ContentBlock> contentBlocks = blogPostRequest.getBlocks().stream()
                .map(request -> ContentBlockRequestToContentBlock.mapToEntity(request))
                .collect(Collectors.toList());


        BlogPost blogBuilder = BlogPost.builder()
                .slug(finalFormattedSlug)
                .title(blogPostRequest.getTitle())
                .authorName(blogPostRequest.getAuthorName())
                .createdAt(LocalDateTime.now())
                .blocks(contentBlocks)
                .tags(blogPostRequest.getTags().stream().collect(Collectors.toSet()))
                .build();

        blogPostRepository.save(blogBuilder);
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
    public List<String> matchingBaseSlugs(String slug) {
        return List.of();
    }

    @Override
    public Integer existsBySlug(String slug) {
        return 0;
    }
}
