package com.nijimas.api.infrastructure.mybatis.mapper;

import com.nijimas.api.core.entity.DailyActivitySummaryEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DailyActivitySummaryMapper {

    void insert(DailyActivitySummaryEntity summary);

    void update(DailyActivitySummaryEntity summary);

    DailyActivitySummaryEntity findOne(DailyActivitySummaryEntity summary);
}
