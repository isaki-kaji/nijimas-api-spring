package com.nijimas.api.application.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateParam {

    @NotBlank(message = "can't be empty")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "must contain only alphanumeric characters")
    private String uid;

    @NotBlank(message = "can't be empty")
    @Size(min = 2, max = 14, message = "must be between 2 and 14 characters long")
    private String username;

    @Size(min = 2, max = 2, message = "must be 2 characters long")
    private String countryCode;
}
