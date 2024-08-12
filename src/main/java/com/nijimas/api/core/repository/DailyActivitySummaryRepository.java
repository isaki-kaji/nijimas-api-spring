package com.nijimas.api.core.repository;

import com.nijimas.api.core.entity.DailyActivitySummaryEntity;

import java.util.Optional;

public interface DailyActivitySummaryRepository {

    void save(DailyActivitySummaryEntity summary);

    void update(DailyActivitySummaryEntity summary);

    Optional<DailyActivitySummaryEntity> findOne(DailyActivitySummaryEntity summary);
}
