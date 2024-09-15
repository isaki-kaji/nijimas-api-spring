package com.nijimas.api.core.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class PostSubcategoryEntity {
    private UUID postId;
    private String categoryNo;
    private UUID categoryId;

    public PostSubcategoryEntity(UUID postId,UUID categoryId, String categoryNo) {
        this.postId = postId;
        this.categoryNo = categoryNo;
        this.categoryId = categoryId;
    }
}
