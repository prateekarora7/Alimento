package com.alimento.prototype.exceptions;

public class UsernameNotFoundException extends RuntimeException{

    public UsernameNotFoundException(String message){
        super(message);
    }

}
