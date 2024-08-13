package com.nijimas.api.infrastructure.mybatis.mapper;

import com.nijimas.api.core.dto.summary.CommonSummaryDto;
import com.nijimas.api.core.entity.SubCategorySummaryEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SubCategorySummaryMapper {

    void insert(SubCategorySummaryEntity summary);

    void update(SubCategorySummaryEntity summary);

    SubCategorySummaryEntity findOne(SubCategorySummaryEntity summary);

    List<CommonSummaryDto> findByMonth(String uid, Integer year, Integer month);
}
