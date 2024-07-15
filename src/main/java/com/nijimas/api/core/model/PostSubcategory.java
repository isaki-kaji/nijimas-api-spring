package com.nijimas.api.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class PostSubcategory {
    private Long postSubCategoryId;
    private UUID postId;
    private String subcategoryNo;
    private String subCategory;

    public PostSubcategory(UUID postId, String subcategoryNo, String subCategory) {
        this.postId = postId;
        this.subcategoryNo = subcategoryNo;
        this.subCategory = subCategory;
    }
}
