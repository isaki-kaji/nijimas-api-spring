package com.nijimas.api.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Getter
@NoArgsConstructor
public class SubCategory {

    private String categoryName;
    private OffsetDateTime createdAt;

    public SubCategory(String categoryName) {
        this.categoryName = categoryName;
    }

}
