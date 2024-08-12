package com.nijimas.api.core.repository;

import com.nijimas.api.core.entity.SubCategorySummaryEntity;

import java.util.Optional;

public interface SubCategoryExpenseSummaryRepository {

    void save(SubCategorySummaryEntity summary);

    void update(SubCategorySummaryEntity summary);

    Optional<SubCategorySummaryEntity> findOne(SubCategorySummaryEntity summary);
}
