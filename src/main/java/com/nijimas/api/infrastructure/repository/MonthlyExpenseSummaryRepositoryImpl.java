package com.nijimas.api.infrastructure.repository;

import com.nijimas.api.core.entity.MonthlySummaryEntity;
import com.nijimas.api.core.repository.MonthlyExpenseSummaryRepository;
import com.nijimas.api.infrastructure.mybatis.mapper.MonthlySummaryMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class MonthlyExpenseSummaryRepositoryImpl implements MonthlyExpenseSummaryRepository {
    private final MonthlySummaryMapper mapper;
    @Override
    public void save(MonthlySummaryEntity summary) {
        mapper.insert(summary);
    }

    @Override
    public void update(MonthlySummaryEntity summary) {
        mapper.update(summary);
    }

    @Override
    public Optional<MonthlySummaryEntity> findOne(MonthlySummaryEntity summary) {
        return Optional.ofNullable(mapper.findOne(summary));
    }
}
