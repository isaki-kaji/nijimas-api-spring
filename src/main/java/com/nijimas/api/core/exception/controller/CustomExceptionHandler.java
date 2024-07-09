package com.nijimas.api.core.exception.controller;

import com.nijimas.api.core.exception.service.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String USER_ALREADY_EXISTS_MESSAGE = "user already exists";

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Object> handleUserAlreadyExists() {
        ErrorResponse errorResponse = new ErrorResponse(USER_ALREADY_EXISTS_MESSAGE);
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
}
