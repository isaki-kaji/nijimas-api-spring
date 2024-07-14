package com.nijimas.api.core.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

public class ApiErrorResponse {

    @JsonProperty("message")
    final private String message;

    public ApiErrorResponse(Exception e) {
        this.message = e.getMessage();
    }

    public ApiErrorResponse(String message) {
        this.message = message;
    }
}
