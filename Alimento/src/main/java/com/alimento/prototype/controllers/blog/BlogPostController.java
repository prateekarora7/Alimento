package com.alimento.prototype.controllers.blog;

import com.alimento.prototype.dtos.blog.BlogPostDTO;
import com.alimento.prototype.entities.blogs.BlogPost;
import com.alimento.prototype.services.blog.BlogPostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blog")
public class BlogPostController {

    private final BlogPostService blogPostService;

    public BlogPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @PostMapping("/save")
    public ResponseEntity saveBlogPostDTO(@RequestBody BlogPostDTO blogPostDTO){

        //saving the auto generated blog Id in a variable
        blogPostService.saveBlogPostDTO(blogPostDTO);

        // returning the blogId with the response entity
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/id/{blogId}")
    public ResponseEntity<BlogPost> getBlogPostById(@PathVariable long blogId){
        return new ResponseEntity<>(blogPostService.getBlogPostbyId(blogId), HttpStatus.OK);
    }

    @GetMapping("/{slug}")
    public ResponseEntity<BlogPost> getBlogPostBySlug(@PathVariable String slug){
        return new ResponseEntity<>(blogPostService.getBlogPostBySlug(slug), HttpStatus.OK);
    }

}
