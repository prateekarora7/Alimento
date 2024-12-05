package com.alimento.prototype.controllers;

import com.alimento.prototype.dtos.CommentDTO;
import com.alimento.prototype.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    //This Delete method is deleting the comment using the comment Id
    //If method returns Ok, then the request was successful. If it return Internal server error, then the request was not successful.
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteComment(@RequestBody int commentId){
        boolean response = commentService.deleteComment(commentId);

        if(response == true) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
