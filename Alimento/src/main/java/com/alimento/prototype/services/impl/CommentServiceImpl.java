package com.alimento.prototype.services.impl;

import com.alimento.prototype.dtos.CommentDTO;
import com.alimento.prototype.entities.Comment;
import com.alimento.prototype.entities.User;
import com.alimento.prototype.exceptions.UserIdNotFoundException;
import com.alimento.prototype.repositories.CommentRepository;
import com.alimento.prototype.repositories.UserRepository;
import com.alimento.prototype.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    //Using Constructor Injection to inject Comment Repository
    @Autowired
    private final CommentRepository commentRepository;

    //Using Constructor Injection to inject User Repository
    @Autowired
    private final UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }


    @Override
    public void saveComment(CommentDTO commentDTO) {

        //Extracting the user using user Id
        User user = userRepository.getUserByUserId(commentDTO.getUserId())
                .orElseThrow(() -> new UserIdNotFoundException("User not found for user id : "+ commentDTO.getUserId()));

        //Building our comment
        Comment comment = Comment.builder()
                            .comment(commentDTO.getCommentContent())
                            .blogId(commentDTO.getBlogId())
                            .user(user)
                            .build();

        //Passing our built comment to comment repository for saving
        commentRepository.saveComment(comment);
    }

    //Method to delete comment using comment Id
    @Override
    public boolean deleteComment(int commentId){
        commentRepository.deleteComment(commentId);

        //Validating if the deletion of comment is successful or not
        if(commentRepository.getCommentById(commentId) == null){
            return true;
        }else{
            return false;
        }
    }
}
