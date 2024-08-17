package com.nijimas.api.core.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ApiErrorResponse {

    @JsonProperty("message")
    private final String message;

    @JsonProperty("errors")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<ErrorDetail> errors;

    public ApiErrorResponse(Exception e) {
        this.message = e.getMessage();
    }

    public ApiErrorResponse(String message) {
        this.message = message;
    }
}
