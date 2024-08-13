package com.nijimas.api.infrastructure.repository;

import com.nijimas.api.core.entity.SubCategorySummaryEntity;
import com.nijimas.api.core.repository.SubCategorySummaryRepository;
import com.nijimas.api.infrastructure.mybatis.mapper.SubCategorySummaryMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class SubCategorySummaryRepositoryImpl implements SubCategorySummaryRepository {
    private final SubCategorySummaryMapper mapper;

    @Override
    public void save(SubCategorySummaryEntity summary) {
        mapper.insert(summary);
    }

    @Override
    public void update(SubCategorySummaryEntity summary) {
        mapper.update(summary);
    }

    @Override
    public Optional<SubCategorySummaryEntity> findOne(SubCategorySummaryEntity summary) {
        return Optional.ofNullable(mapper.findOne(summary));
    }
}
