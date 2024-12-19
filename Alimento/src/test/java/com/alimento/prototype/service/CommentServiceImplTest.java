package com.alimento.prototype.service;

import com.alimento.prototype.dtos.comment.CommentDTO;
import com.alimento.prototype.entities.user.User;
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

}
