package com.nnk.springboot.exeption;

public class UserAlreadyExistingException extends Exception {



    public UserAlreadyExistingException(String message) {
        super(message);
    }
}
