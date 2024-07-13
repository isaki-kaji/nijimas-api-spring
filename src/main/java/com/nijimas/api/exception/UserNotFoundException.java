package com.nijimas.api.exception;
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String uid) {
        super("User with uid " + uid + " not found");
    }
}