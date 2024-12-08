package com.alimento.prototype.controllers.blog;

import com.alimento.prototype.dtos.blog.BlogPostDTO;
import com.alimento.prototype.services.blog.BlogPostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog")
public class BlogPostController {

    private final BlogPostService blogPostService;

    public BlogPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @PostMapping("/save")
    public ResponseEntity<Long> saveBlogPostDTO(@RequestBody BlogPostDTO blogPostDTO){

        //saving the auto generated blog Id in a variable
        long blogId = blogPostService.saveBlogPostDTO(blogPostDTO);

        // returning the blogId with the response entity
        return new ResponseEntity(blogId, HttpStatus.CREATED);
    }

}
