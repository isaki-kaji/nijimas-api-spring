package com.nijimas.api.application.service.user;

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
public class UpdateUserParam {

    @JsonIgnore
    private String uid;

    @NotBlank(message = "username is mandatory")
    @Size(min = 2, max = 14, message = "username must be between 2 and 14 characters long")
    private String username;

    @Size(min = 2, max = 2, message = "country_code must be 2 characters long")
    private String countryCode;

    @Size(max = 200, message = "self_intro must be 200 characters or less")
    private String selfIntro;

    @Size(max = 2000, message = "profile_image_url must be 2000 characters or less")
    private String profileImageUrl;
}
