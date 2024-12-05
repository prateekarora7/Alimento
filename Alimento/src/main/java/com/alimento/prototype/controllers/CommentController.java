package com.alimento.prototype.controllers;

import com.alimento.prototype.dtos.CommentDTO;
import com.alimento.prototype.services.CommentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
