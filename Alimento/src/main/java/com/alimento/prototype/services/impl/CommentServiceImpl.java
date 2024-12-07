package com.alimento.prototype.services.impl;

import com.alimento.prototype.dtos.CommentDTO;
import com.alimento.prototype.entities.Comment;
import com.alimento.prototype.entities.User;
import com.alimento.prototype.exceptions.NoCommentsFoundException;
import com.alimento.prototype.exceptions.UserIdNotFoundException;
import com.alimento.prototype.repositories.CommentRepository;
import com.alimento.prototype.repositories.UserRepository;
import com.alimento.prototype.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.ConnectException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

        //Passing our built comment to comment repository for saving
        commentRepository.saveComment(commentDTO.getCommentContent(), commentDTO.getBlogId(), commentDTO.getUserId(), LocalDate.now());
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

    //Method to get comments by a user using userId
    @Override
    public List<Comment> getCommentsByUserId(String userId) {
        List<Comment> comments = commentRepository.getCommentsByUserId(userId);

        //Checking if there are no comments associated with the User Id then we will throw an exception
        if(comments.size() == 0){
            Optional<User> user = userRepository.getUserByUserId(userId);
            throw new NoCommentsFoundException("No comments found for this user : " + user.get().getEmail());
        }
        // else we will return comments as a list
        return comments;
    }

    @Override
    public List<Comment> getCommentsByBlogId(int blogId){
        List<Comment> comments = commentRepository.getCommentsByBlogId(blogId);

        if(comments.size() == 0){
            // we have to fetch the blog title here and pass it with the exception message just like in the above method (getCommentsByUserId)
            //No comments found exception will be thrown
        }
        return comments;
    }

}
