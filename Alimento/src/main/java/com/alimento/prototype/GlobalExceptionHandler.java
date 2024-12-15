package com.alimento.prototype;

import com.alimento.prototype.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    //This is handling exception for user not found for given user id
    @ExceptionHandler({UsernameNotFoundException.class, EmailNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND) // Set the response status
    public ResponseEntity<Map<String, String>> handleUserNotFoundException(String message){
        Map<String, String> error = new HashMap<>();

        error.put("message", message);
        error.put("Status Code", String.valueOf(HttpStatus.NOT_FOUND));

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    //This is hadnling exception for comment not found for given comment id
    @ExceptionHandler(CommentIdNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // Set the response status
    public ResponseEntity<Map<String, String>> handleCommentNotFoundException(String message){
        Map<String, String> error = new HashMap<>();

        error.put("message", message);
        error.put("Status Code", String.valueOf(HttpStatus.NOT_FOUND));

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    //This is handling exception for user already exists after validating email exists in the database
    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT) // Set the response status
    public ResponseEntity<Map<String, String>> handleUserAlreadyExistsException(String message){
        Map<String, String> error = new HashMap<>();

        error.put("message", message);
        error.put("Status Code", String.valueOf(HttpStatus.CONFLICT));

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    //This is handling exception when there are no comments associated for a user Id or Blog Id
    @ExceptionHandler(NoCommentsFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Map<String, String>> handleNoCommentsFoundException(String message){
        Map<String, String> error = new HashMap<>();

        error.put("message", message);
        error.put("Status Code", String.valueOf(HttpStatus.NOT_FOUND));

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
