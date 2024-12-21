package com.alimento.prototype.services.implementation.comment;

import com.alimento.prototype.dtos.comment.CommentDTO;
import com.alimento.prototype.entities.comment.Comment;
import com.alimento.prototype.entities.user.User;
import com.alimento.prototype.exceptions.NoCommentsFoundException;
import com.alimento.prototype.exceptions.UsernameNotFoundException;
import com.alimento.prototype.repositories.comment.CommentRepository;
import com.alimento.prototype.repositories.user.UserRepository;
import com.alimento.prototype.services.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        User user = userRepository.getUserByUsername(commentDTO.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found for user id : "+ commentDTO.getUsername()));

        //Passing our built comment to comment repository for saving
        commentRepository.saveComment(commentDTO.getCommentContent(), commentDTO.getBlogId(), commentDTO.getUsername(), LocalDate.now());
    }

    @Override
    public Comment getCommentByCommentId(long commentId){
        return commentRepository.getCommentByCommentId(commentId);
    }

    @Override
    public List<Comment> getCommentsByBlogIdAndUsername(int blogId, String username) {
        return commentRepository.getCommentsByBlogIdAndUsername(blogId, username);
    }

    //Method to delete comment using comment Id
    @Override
    public void deleteComment(long commentId){
        commentRepository.deleteComment(commentId);

        //Throwing run time exception if resource deletion was not succesful
        if(!(commentRepository.getCommentByCommentId(commentId) == null)){
            throw new RuntimeException("Resource deletion was unsuccesful");
        }
    }

    //Method to get comments by a user using userId
    @Override
    public List<Comment> getCommentsByUserId(String username) {
        List<Comment> comments = commentRepository.getCommentsByUsername(username);

        //Checking if there are no comments associated with the User Id then we will throw an exception
        if(comments.size() == 0){
            Optional<User> user = userRepository.getUserByUsername(username);
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

    @Override
    public Comment updateComment(long commentId, String commentContent) {
        commentRepository.updateComment(commentId, commentContent);
        return commentRepository.getCommentByCommentId(commentId);
    }

}
