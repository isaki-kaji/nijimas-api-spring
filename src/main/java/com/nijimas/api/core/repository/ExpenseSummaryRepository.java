package com.nijimas.api.core.repository;

import com.nijimas.api.core.dto.summary.ExpenseSummaryDto;
import com.nijimas.api.core.entity.ExpenseSummaryEntity;

import java.util.Optional;

public interface ExpenseSummaryRepository {

    void save(ExpenseSummaryEntity summary);

    void update(ExpenseSummaryEntity summary);

    Optional<ExpenseSummaryEntity> findOne(ExpenseSummaryEntity summary);

    Optional<ExpenseSummaryDto> findByMonth(String uid, Integer year, Integer month);
}
