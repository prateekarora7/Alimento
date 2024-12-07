package com.alimento.prototype.exceptions;

public class NoCommentsFoundException extends RuntimeException{
    public NoCommentsFoundException(String message){
        super(message);
    }
}
