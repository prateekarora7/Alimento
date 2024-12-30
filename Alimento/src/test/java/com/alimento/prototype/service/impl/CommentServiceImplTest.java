package com.alimento.prototype.service.impl;

import com.alimento.prototype.dtos.comment.CommentDTO;
import com.alimento.prototype.entities.blog.BlogPost;
import com.alimento.prototype.entities.comment.Comment;
import com.alimento.prototype.entities.user.User;
import com.alimento.prototype.exceptions.NoCommentsFoundException;
import com.alimento.prototype.exceptions.UsernameNotFoundException;
import com.alimento.prototype.repositories.comment.CommentRepository;
import com.alimento.prototype.repositories.user.UserRepository;
import com.alimento.prototype.services.implementation.comment.CommentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CommentServiceImplTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveComment_success(){
        CommentDTO commentDTO = new CommentDTO("Great Post!", 101, "test_user");

        User user = new User();
        user.setEmail("testemail@gmail.com");
        user.setPassword("12345678");
        user.setUsername("test_user");
        user.setFirstName("Test");
        user.setLastName("User");
        user.setPhoneNo("9876543210");
        user.setCreatedAt(LocalDateTime.now());

        when(userRepository.getUserByUsername("test_user")).thenReturn(Optional.of(user));

        assertDoesNotThrow(() -> commentService.saveComment(commentDTO));

        verify(userRepository, times(1)).getUserByUsername("test_user");
        verify(commentRepository, times(1))
                .saveComment("Great Post!", 101, "test_user", LocalDate.now());
    }

    @Test
    public void testSaveComment_UserNotFound() {
        // Arrange
        CommentDTO commentDTO = new CommentDTO("Great Post!", 101, "test_user");

        when(userRepository.getUserByUsername("test_user")).thenReturn(Optional.empty());

        // Act & Assert
        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class, () -> {
            commentService.saveComment(commentDTO);
        });

        assertEquals("User not found for user id : test_user", exception.getMessage());
        verify(userRepository, times(1)).getUserByUsername("test_user");
        verify(commentRepository, never()).saveComment(any(), anyInt(), any(), any());
    }

    @Test
    public void testDeleteComment_Success() {
        // Arrange
        Comment comment = new Comment();
        comment.setCommentId(1);
        comment.setUser(new User());
        comment.setCommentContent("Great Post!!!");
        comment.setCommentDate(LocalDate.now());


        when(commentRepository.getCommentByCommentId(1)).thenReturn(null);

        // Act
        assertDoesNotThrow(() -> commentService.deleteComment(1));

        // Assert
        verify(commentRepository, times(1)).deleteComment(1);
        verify(commentRepository, times(1)).getCommentByCommentId(1);
    }

    @Test
    public void testDeleteComment_Failure() {
        // Arrange
        Comment comment = new Comment(1, "Nice!", new BlogPost(), new User(), LocalDate.now());
        when(commentRepository.getCommentByCommentId(1)).thenReturn(comment);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            commentService.deleteComment(1);
        });

        assertEquals("Resource deletion was unsuccesful", exception.getMessage());
        verify(commentRepository, times(1)).deleteComment(1);
        verify(commentRepository, times(1)).getCommentByCommentId(1);
    }

    // Test for getCommentsByUserId method
    @Test
    public void testGetCommentsByUserId_NoCommentsFound() {
        // Arrange
        when(commentRepository.getCommentsByUsername("test_user")).thenReturn(List.of());
        User user = new User("testuser@gmail.com", "testpassword", "test_user", "test", "user", "9012388384", LocalDateTime.now(), List.of());
        when(userRepository.getUserByUsername("test_user")).thenReturn(Optional.of(user));

        // Act & Assert
        NoCommentsFoundException exception = assertThrows(NoCommentsFoundException.class, () -> {
            commentService.getCommentsByUserId("test_user");
        });

        assertEquals("No comments found for this user : testuser@gmail.com", exception.getMessage());
        verify(commentRepository, times(1)).getCommentsByUsername("test_user");
        verify(userRepository, times(1)).getUserByUsername("test_user");
    }

    @Test
    public void testGetCommentsByUserId_Success() {
        // Arrange
        Comment comment1 = new Comment(1, "Nice Post 1!!!", new BlogPost(), new User(), LocalDate.now());
        Comment comment2 = new Comment(2, "Nice Post 2!!!", new BlogPost(), new User(), LocalDate.now());
        List<Comment> comments = List.of(comment1, comment2);

        when(commentRepository.getCommentsByUsername("test_user")).thenReturn(comments);

        // Act
        List<Comment> result = commentService.getCommentsByUserId("test_user");

        // Assert
        assertEquals(2, result.size());
        verify(commentRepository, times(1)).getCommentsByUsername("test_user");
        verify(userRepository, never()).getUserByUsername(any());
    }

    // Test for updateComment method
    @Test
    public void testUpdateComment_Success() {
        // Arrange
        Comment updatedComment = new Comment(1, "Updated Content", new BlogPost(), new User(), LocalDate.now());

        when(commentRepository.getCommentByCommentId(1)).thenReturn(updatedComment);

        // Act
        Comment result = commentService.updateComment(1, "Updated Content");

        // Assert
        assertEquals("Updated Content", result.getCommentContent());
        verify(commentRepository, times(1)).updateComment(1, "Updated Content");
        verify(commentRepository, times(1)).getCommentByCommentId(1);
    }
}
