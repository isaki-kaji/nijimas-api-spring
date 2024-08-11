package com.nijimas.api.infrastructure.repository;

import com.nijimas.api.core.entity.SubCategoryExpenseSummaryEntity;
import com.nijimas.api.core.repository.SubCategoryExpenseSummaryRepository;
import com.nijimas.api.infrastructure.mybatis.mapper.SubCategoryExpenseSummaryMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class SubCategoryExpenseSummaryRepositoryImpl implements SubCategoryExpenseSummaryRepository {
    private final SubCategoryExpenseSummaryMapper mapper;

    @Override
    public void save(SubCategoryExpenseSummaryEntity summary) {
        mapper.insert(summary);
    }

    @Override
    public void update(SubCategoryExpenseSummaryEntity summary) {
        mapper.update(summary);
    }

    @Override
    public Optional<SubCategoryExpenseSummaryEntity> findOne(SubCategoryExpenseSummaryEntity summary) {
        return Optional.ofNullable(mapper.findOne(summary));
    }
}
