package com.nijimas.api.infrastructure.repository;

import com.nijimas.api.core.dto.summary.ExpenseSummaryDto;
import com.nijimas.api.core.entity.ExpenseSummaryEntity;
import com.nijimas.api.core.repository.ExpenseSummaryRepository;
import com.nijimas.api.infrastructure.mybatis.mapper.ExpenseSummaryMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class ExpenseSummaryRepositoryImpl implements ExpenseSummaryRepository {
    private final ExpenseSummaryMapper mapper;
    @Override
    public void save(ExpenseSummaryEntity summary) {
        mapper.insert(summary);
    }

    @Override
    public void update(ExpenseSummaryEntity summary) {
        mapper.update(summary);
    }

    @Override
    public Optional<ExpenseSummaryEntity> findOne(ExpenseSummaryEntity summary) {
        return Optional.ofNullable(mapper.findOne(summary));
    }

    @Override
    public Optional<ExpenseSummaryDto> findByMonth(String uid, Integer year, Integer month) {
        return Optional.ofNullable(mapper.findByMonth(uid, year, month));
    }
}