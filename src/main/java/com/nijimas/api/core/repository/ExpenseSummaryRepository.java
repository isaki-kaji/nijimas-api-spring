package com.nijimas.api.core.repository;

import com.nijimas.api.core.entity.ExpenseSummaryEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ExpenseSummaryRepository {

    void save(ExpenseSummaryEntity summary);

    void update(ExpenseSummaryEntity summary);

    Optional<ExpenseSummaryEntity> findOne(ExpenseSummaryEntity summary);

    Map<String, BigDecimal> findByMonth(String uid, Integer year, Integer month);
}
