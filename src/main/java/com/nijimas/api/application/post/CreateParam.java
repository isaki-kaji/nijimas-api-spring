package com.nijimas.api.application.post;

import com.nijimas.api.core.constant.MessageConstants;
import com.nijimas.api.core.constant.RegexpConstants;
import jakarta.validation.constraints.Max;
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
public class CreateParam {

    @NotBlank(message = "can't be empty")
    @Pattern(regexp = RegexpConstants.UUID_PATTERN, message = "must be a valid UUID")
    private String postId;

    private String uid;

    @NotBlank(message = "can't be empty")
    @Size(max = 255, message = "must be less than 255 characters")
    @Pattern(regexp = RegexpConstants.MAIN_CATEGORY_PATTERN, message = MessageConstants.MAIN_CATEGORY_INVALID)
    private String mainCategory;

    @Size(max = 255, message = "must be less than 255 characters")
    private String subCategory1;

    @Size(max = 255, message = "must be less than 255 characters")
    private String subCategory2;

    @Size(max = 200, message = "must be less than 200 characters")
    private String postText;

    @Size(max = 2000, message = "must be less than 2000 characters")
    private String photoUrl;

    @Max(value = 99999999, message = "must be less than 100000000")
    private Integer expense;

    private String location;

    @NotBlank(message = "can't be empty")
    @Pattern(regexp = RegexpConstants.PUBLIC_TYPE_NO_PATTERN, message = "must be one of 1, 2, or 3")
    private String publicTypeNo;
}
