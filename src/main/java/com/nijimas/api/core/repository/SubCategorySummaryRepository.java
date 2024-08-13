package com.nijimas.api.core.repository;

import com.nijimas.api.core.entity.SubCategorySummaryEntity;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public interface SubCategorySummaryRepository {

    void save(SubCategorySummaryEntity summary);

    void update(SubCategorySummaryEntity summary);

    Optional<SubCategorySummaryEntity> findOne(SubCategorySummaryEntity summary);

    Map<String, BigDecimal> findByMonth(String uid, Integer year, Integer month);
}
