package com.nijimas.api.core.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class PostSubcategoryEntity {
    private UUID postId;
    private String subcategoryNo;
    private String subCategory;

    public PostSubcategoryEntity(UUID postId,String subCategory, String subcategoryNo) {
        this.postId = postId;
        this.subcategoryNo = subcategoryNo;
        this.subCategory = subCategory;
    }
}
