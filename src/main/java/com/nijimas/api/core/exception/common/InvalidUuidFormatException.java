package com.nijimas.api.core.exception.common;

public class InvalidUuidFormatException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "The provided UUID string is not in a valid format.";

    public InvalidUuidFormatException() {
        super(DEFAULT_MESSAGE);
    }
}
