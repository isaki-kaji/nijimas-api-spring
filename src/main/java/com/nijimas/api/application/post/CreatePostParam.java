package com.nijimas.api.application.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nijimas.api.core.constant.CommonConstants;
import com.nijimas.api.core.constant.MessageConstants;
import com.nijimas.api.core.constant.RegexpConstants;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostParam {

    @NotBlank(message = "post_id is mandatory")
    @Pattern(regexp = RegexpConstants.UUID_PATTERN, message = "must be a valid UUID")
    private String postId;

    @JsonIgnore
    private String uid;

    @NotBlank(message = "main_category is mandatory")
    @Size(max = 255, message = "main_category must be less than 255 characters")
    @Pattern(regexp = RegexpConstants.MAIN_CATEGORY_PATTERN, message = MessageConstants.MAIN_CATEGORY_INVALID)
    private String mainCategory;

    @Size(max = 255, message = "sub_category1 must be less than 255 characters")
    private String subCategory1;

    @Size(max = 255, message = "sub_category2 must be less than 255 characters")
    private String subCategory2;

    @Size(max = 200, message = "post_text must be less than 200 characters")
    private String postText;

    @Size(max = 2000, message = "photo_url must be less than 2000 characters")
    private String photoUrl;

    @DecimalMax(value = CommonConstants.MAX_EXPENSE_STR, message = "expense must be less than 100000000")
    @DecimalMin(value = CommonConstants.MIN_EXPENSE_STR, message = "expense must be greater than or equal to 0")
    private BigDecimal expense;

    private String location;

    @NotBlank(message = "public type no is mandatory")
    @Pattern(regexp = RegexpConstants.PUBLIC_TYPE_NO_PATTERN, message = "public_type_no must be one of 1, 2, or 3")
    private String publicTypeNo;

    @JsonIgnore
    private OffsetDateTime createdAt = OffsetDateTime.now(ZoneOffset.UTC);
}
