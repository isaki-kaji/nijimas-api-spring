package com.nijimas.api.core.repository;

import com.nijimas.api.core.entity.SubCategoryEntity;

import java.util.Optional;
import java.util.UUID;

public interface SubCategoryRepository {

    void save(SubCategoryEntity subCategoryEntity);

    Optional<SubCategoryEntity> findById(String categoryName);
}
