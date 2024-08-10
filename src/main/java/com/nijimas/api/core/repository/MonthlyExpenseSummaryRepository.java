package com.nijimas.api.core.repository;

import com.nijimas.api.core.entity.MonthlyExpenseSummaryEntity;

import java.util.Optional;

public interface MonthlyExpenseSummaryRepository {

    void save(MonthlyExpenseSummaryEntity summary);

    void update(MonthlyExpenseSummaryEntity summary);

    Optional<MonthlyExpenseSummaryEntity> findOne(MonthlyExpenseSummaryEntity summary);
}
