package com.nijimas.api.exception;

public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException(String uid) {
        super("User with uid " + uid + " already exists");
    }
}
