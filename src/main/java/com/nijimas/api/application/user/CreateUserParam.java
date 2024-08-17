package com.nijimas.api.application.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserParam {

    @JsonIgnore
    private String uid;

    @NotBlank(message = "uid is mandatory")
    @Size(min = 2, max = 14, message = "must be between 2 and 14 characters long")
    private String username;

    @Size(min = 2, max = 2, message = "country_code must be 2 characters long")
    private String countryCode;
}
