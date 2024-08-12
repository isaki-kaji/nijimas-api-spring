package com.nijimas.api.infrastructure.repository;

import com.nijimas.api.core.entity.DailyActivitySummaryEntity;
import com.nijimas.api.core.repository.DailyActivitySummaryRepository;
import com.nijimas.api.infrastructure.mybatis.mapper.DailyActivitySummaryMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class DailyActivitySummaryRepositoryImpl implements DailyActivitySummaryRepository {
    private final DailyActivitySummaryMapper mapper;

    @Override
    public void save(DailyActivitySummaryEntity summary) {
        mapper.insert(summary);
    }

    @Override
    public void update(DailyActivitySummaryEntity summary) {
        mapper.update(summary);
    }

    @Override
    public Optional<DailyActivitySummaryEntity> findOne(DailyActivitySummaryEntity summary) {
        return Optional.ofNullable(mapper.findOne(summary));
    }
}
