package com.nijimas.api.infrastructure.mybatis.mapper;

import com.nijimas.api.core.entity.MonthlySummaryEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MonthlySummaryMapper {

    void insert(MonthlySummaryEntity summary);

    void update(MonthlySummaryEntity summary);

    MonthlySummaryEntity findOne(MonthlySummaryEntity summary);
}
