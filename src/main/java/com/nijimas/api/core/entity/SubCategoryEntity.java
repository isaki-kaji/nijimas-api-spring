package com.nijimas.api.core.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Getter
@NoArgsConstructor
public class SubCategoryEntity {

    private String categoryName;
    private OffsetDateTime createdAt;

    public SubCategoryEntity(String categoryName) {
        this.categoryName = categoryName;
    }

}
