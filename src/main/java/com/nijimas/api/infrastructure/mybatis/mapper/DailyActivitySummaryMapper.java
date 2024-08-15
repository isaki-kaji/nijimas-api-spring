package com.nijimas.api.infrastructure.mybatis.mapper;

import com.nijimas.api.core.dto.summary.DailyActivitySummaryDto;
import com.nijimas.api.core.entity.DailyActivitySummaryEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DailyActivitySummaryMapper {

    void insert(DailyActivitySummaryEntity summary);

    void update(DailyActivitySummaryEntity summary);

    DailyActivitySummaryEntity findOne(DailyActivitySummaryEntity summary);

    List<DailyActivitySummaryDto> findByMonth(String uid, Integer year, Integer month);
}
