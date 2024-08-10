package com.nijimas.api.infrastructure.repository;

import com.nijimas.api.core.entity.MonthlyExpenseSummaryEntity;
import com.nijimas.api.core.repository.MonthlyExpenseSummaryRepository;
import com.nijimas.api.infrastructure.mybatis.mapper.MonthlyExpenseSummaryMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class MonthlyExpenseSummaryRepositoryImpl implements MonthlyExpenseSummaryRepository {
    private final MonthlyExpenseSummaryMapper mapper;
    @Override
    public void save(MonthlyExpenseSummaryEntity summary) {
        mapper.insert(summary);
    }

    @Override
    public void update(MonthlyExpenseSummaryEntity summary) {
        mapper.update(summary);
    }

    @Override
    public Optional<MonthlyExpenseSummaryEntity> findOne(MonthlyExpenseSummaryEntity summary) {
        return Optional.ofNullable(mapper.findOne(summary));
    }
}
