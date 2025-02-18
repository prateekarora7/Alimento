package com.alimento.prototype.controllers.comment;

import com.alimento.prototype.dtos.comment.CommentDTO;
import com.alimento.prototype.entities.comment.Comment;
import com.alimento.prototype.services.comment.CommentService;
import org.springframework.data.repository.query.Param;
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
    @PostMapping("/save")
    public void saveComment(@RequestBody CommentDTO commentDTO){
        commentService.saveComment(commentDTO);
    }

    //This method return comment after comparing comment Id
    @GetMapping("/id/{id}")
    public ResponseEntity<Comment> getCommentsByCommentId(@PathVariable Long commentId){
        return new ResponseEntity<>(commentService.getCommentByCommentId(commentId), HttpStatus.OK);
    }

    //This method return all the comments of a user in a list<Comment> after comparing username
    @GetMapping("/user/{username}")
    public ResponseEntity<List<Comment>> getCommentsByUserId(@PathVariable String username){
        return new ResponseEntity<>(commentService.getCommentsByUserId(username), HttpStatus.OK);
    }

    //This method return all the comments of a blog in a list<Comment> after comparing blog Id
    @GetMapping("/blog/{blogId}")
    public ResponseEntity<List<Comment>> getCommentByBlogId(@PathVariable int blogId){
        return new ResponseEntity<>(commentService.getCommentsByBlogId(blogId), HttpStatus.OK);
    }

    //This method return list<Comment> after matching both blogId and Username
    @GetMapping("blogid-username/{blogId}/{username}")
    public ResponseEntity<List<Comment>> getCommentsByBlogIdAndUsername(@PathVariable int blogId, @PathVariable String username){
        return new ResponseEntity<>(commentService.getCommentsByBlogIdAndUsername(blogId, username), HttpStatus.OK);
    }

    //This delete method is removing the comment using the comment Id
    //If method returns Ok, then the request was successful. If it returns Internal server error, then the request was not successful.
    @DeleteMapping("/delete")
    public ResponseEntity deleteComment(@PathVariable long commentId){
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //This update method updates just the content of the comment
    @PutMapping("/update")
    public ResponseEntity<Comment> updateComment(@PathVariable long commentId, @RequestBody String commentContent){
        commentService.updateComment(commentId, commentContent);
        return new ResponseEntity<>(commentService.getCommentByCommentId(commentId), HttpStatus.CREATED);
    }

}
