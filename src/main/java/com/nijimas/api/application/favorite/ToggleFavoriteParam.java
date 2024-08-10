package com.nijimas.api.application.favorite;

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

    @NotBlank(message = "can't be empty")
    @Pattern(regexp = RegexpConstants.UUID_PATTERN, message = "must be a valid UUID")
    private String postId;

    private String uid;
}
