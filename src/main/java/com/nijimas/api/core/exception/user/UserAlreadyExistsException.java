package com.nijimas.api.core.exception.user;

public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException(String uid) {
        super("User with uid " + uid + " already exists");
    }
}
