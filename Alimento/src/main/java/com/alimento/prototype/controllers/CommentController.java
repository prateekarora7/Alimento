package com.alimento.prototype.controllers;

import com.alimento.prototype.dtos.CommentDTO;
import com.alimento.prototype.entities.Comment;
import com.alimento.prototype.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    //Creating a constructor injection for the comment service interface
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //This Post methods take comment DTO as body and maps it to comment DTO class.
    //This method also uses comment service method saveComment to save the comment
    @PostMapping("/save-new")
    public void saveComment(@RequestBody CommentDTO commentDTO){
        commentService.saveComment(commentDTO);
    }

    @GetMapping("/id/{userId}")
    public ResponseEntity<List<Comment>> getCommentsByUserId(@PathVariable String userId){
        return new ResponseEntity<>(commentService.getCommentsByUserId(userId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getCommentByBlogId(@PathVariable int blogId){
        return new ResponseEntity<>(commentService.getCommentsByBlogId(blogId), HttpStatus.OK);
    }
    //This delete method is removing the comment using the comment Id
    //If method returns Ok, then the request was successful. If it returns Internal server error, then the request was not successful.
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteComment(@RequestBody int commentId){
        boolean response = commentService.deleteComment(commentId);

        if(response == true) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
