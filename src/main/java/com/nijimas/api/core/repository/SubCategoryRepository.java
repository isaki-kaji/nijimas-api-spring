package com.nijimas.api.core.repository;

import com.nijimas.api.core.entity.SubCategoryEntity;

import java.util.Optional;

public interface SubCategoryRepository {

    void save(String categoryName);

    Optional<SubCategoryEntity> findById(String categoryName);
}
