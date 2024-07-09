package com.nijimas.api.application.user;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@JsonRootName("user")
@AllArgsConstructor
@NoArgsConstructor
public class CreateParam {

    @NotBlank(message = "can't be empty")
    @Pattern(regexp = "[a-zA-Z0-9]+$", message = "must contain only alphanumeric characters")
    private String uid;

    @NotBlank(message = "can't be empty")
    @Size(min = 2, max = 14, message = "must be between 2 and 14 characters long")
    private String username;

    @Size(min = 2, max = 2, message = "must be 2 characters long")
    private String countryCode;
}
