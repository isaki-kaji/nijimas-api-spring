package com.nijimas.api.core.repository;

import com.nijimas.api.core.entity.MonthlySummaryEntity;

import java.util.Optional;

public interface MonthlyExpenseSummaryRepository {

    void save(MonthlySummaryEntity summary);

    void update(MonthlySummaryEntity summary);

    Optional<MonthlySummaryEntity> findOne(MonthlySummaryEntity summary);
}
