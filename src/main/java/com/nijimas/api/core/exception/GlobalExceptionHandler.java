package com.nijimas.api.core.exception;

import com.nijimas.api.core.exception.common.ForbiddenException;
import com.nijimas.api.core.exception.common.InvalidUuidFormatException;
import com.nijimas.api.core.exception.common.UnauthorizedException;
import com.nijimas.api.util.CommonUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Object> handleUnauthorizedException(UnauthorizedException e) {
        final ApiErrorResponse errorResponse = new ApiErrorResponse(e);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = bindingResult.getFieldErrors().stream()
                .map(this::createValidationErrorMessage)
                .collect(Collectors.joining(" , "));
        final ApiErrorResponse errorResponse = new ApiErrorResponse(message);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<Object> handleForbiddenException(ForbiddenException e) {
        final ApiErrorResponse errorResponse = new ApiErrorResponse(e);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }

    @ExceptionHandler(InvalidUuidFormatException.class)
    public ResponseEntity<Object> handleBadRequest(Exception e) {
        final ApiErrorResponse errorResponse = new ApiErrorResponse(e);
        return ResponseEntity.badRequest().body(errorResponse);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Object> handleOtherException(Exception e) {
//        ApiErrorResponse errorResponse = new ApiErrorResponse(e.getMessage());
//        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    private String createValidationErrorMessage(FieldError fe) {
        String field = CommonUtil.toSnakeCase(fe.getField());
        return String.format("Field '%s' %s (rejected value: %s)",
                field, fe.getDefaultMessage(), fe.getRejectedValue());
    }
}
