package com.nijimas.api.application.service.favorite;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nijimas.api.core.constant.RegexpConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ToggleFavoriteParam {

    @JsonIgnore
    private String uid;

    @NotBlank(message = "post_id is mandatory")
    @Pattern(regexp = RegexpConstants.UUID_PATTERN, message = "must be a valid UUID")
    private String postId;
}
