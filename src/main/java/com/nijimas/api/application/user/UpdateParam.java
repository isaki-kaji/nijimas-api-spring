package com.nijimas.api.application.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateParam {

    private String uid;

    @NotBlank(message = "can't be empty")
    @Size(min = 2, max = 14, message = "must be between 2 and 14 characters long")
    private String username;

    @Size(min = 2, max = 2, message = "must be 2 characters long")
    private String countryCode;

    @Size(max = 200, message = "must be 200 characters or less")
    private String selfIntro;

    @Size(max = 2000, message = "must be 2000 characters or less")
    private String profileImageUrl;
}
