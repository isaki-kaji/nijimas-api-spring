package com.nijimas.api.core.exception.common;

public class ForbiddenException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "You are not allowed to access or modify this user's information";

    public ForbiddenException() {
        super(DEFAULT_MESSAGE);
    }
}