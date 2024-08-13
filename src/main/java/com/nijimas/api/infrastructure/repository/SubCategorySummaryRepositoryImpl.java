package com.nijimas.api.infrastructure.repository;

import com.nijimas.api.core.dto.summary.CommonSummaryDto;
import com.nijimas.api.core.entity.SubCategorySummaryEntity;
import com.nijimas.api.core.repository.SubCategorySummaryRepository;
import com.nijimas.api.infrastructure.mybatis.mapper.SubCategorySummaryMapper;
import com.nijimas.api.util.RepositoryUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
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

    @Override
    public Map<String, BigDecimal> findByMonth(String uid, Integer year, Integer month) {
        List<CommonSummaryDto> results = mapper.findByMonth(uid, year, month);
        // dtoのリストをMapに変換
        return RepositoryUtil.toSummaryMap(results);
    }
}
