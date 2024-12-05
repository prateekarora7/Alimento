package com.alimento.prototype;

import com.alimento.prototype.exceptions.CommentIdNotFoundException;
import com.alimento.prototype.exceptions.UserIdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    //This is handling exception for user not found for given user id
    @ExceptionHandler(UserIdNotFoundException.class)
    public Map<String, String> handleUserNotFoundException(String message){
        Map<String, String> error = new HashMap<>();

        error.put("message", message);
        error.put("Status Code", String.valueOf(HttpStatus.NOT_FOUND));

        return error;
    }

    //This is hadnling exception for comment not found for given comment id
    @ExceptionHandler(CommentIdNotFoundException.class)
    public Map<String, String> handleCommentNotFoundException(String message){
        Map<String, String> error = new HashMap<>();

        error.put("message", message);
        error.put("Status Code", String.valueOf(HttpStatus.NOT_FOUND));

        return error;
    }
}
