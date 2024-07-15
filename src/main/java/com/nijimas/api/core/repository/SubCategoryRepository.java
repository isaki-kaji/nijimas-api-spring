package com.nijimas.api.core.repository;

import com.nijimas.api.core.model.SubCategory;

import java.util.Optional;

public interface SubCategoryRepository {

    void save(String categoryName);

    Optional<SubCategory> findById(String categoryName);
}
