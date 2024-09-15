package com.nijimas.api.core.entity;

import com.nijimas.api.util.CommonUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class SubCategoryEntity {

    @Setter
    private UUID categoryId;
    private String categoryName;
    private OffsetDateTime createdAt;

    public SubCategoryEntity(String categoryName) {
        this.categoryId = CommonUtil.generateUuid();
        this.categoryName = categoryName;
    }
}
