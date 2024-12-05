package com.alimento.prototype.exceptions;

public class UserIdNotFoundException extends RuntimeException{

    public UserIdNotFoundException(String message){
        super(message);
    }

}
