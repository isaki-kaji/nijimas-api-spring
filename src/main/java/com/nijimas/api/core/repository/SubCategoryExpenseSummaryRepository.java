package com.nijimas.api.core.repository;

import com.nijimas.api.core.entity.MonthlyExpenseSummaryEntity;
import com.nijimas.api.core.entity.SubCategoryExpenseSummaryEntity;

import java.util.Optional;

public interface SubCategoryExpenseSummaryRepository {

    void save(SubCategoryExpenseSummaryEntity summary);

    void update(SubCategoryExpenseSummaryEntity summary);

    Optional<SubCategoryExpenseSummaryEntity> findOne(SubCategoryExpenseSummaryEntity summary);
}
