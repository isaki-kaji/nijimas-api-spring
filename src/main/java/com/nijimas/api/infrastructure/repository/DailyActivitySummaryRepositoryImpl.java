package com.nijimas.api.infrastructure.repository;

import com.nijimas.api.core.entity.DailyActivitySummaryEntity;
import com.nijimas.api.core.repository.DailyActivitySummaryRepository;

import java.util.Optional;

public class DailyActivitySummaryRepositoryImpl implements DailyActivitySummaryRepository {
    @Override
    public void save(DailyActivitySummaryEntity summary) {

    }

    @Override
    public void update(DailyActivitySummaryEntity summary) {

    }

    @Override
    public Optional<DailyActivitySummaryEntity> findOne(DailyActivitySummaryEntity summary) {
        return Optional.empty();
    }
}
