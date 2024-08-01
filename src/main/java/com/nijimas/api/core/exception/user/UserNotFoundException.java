package com.nijimas.api.core.exception.user;
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String uid) {
        super("User with uid " + uid + " not found");
    }
}
