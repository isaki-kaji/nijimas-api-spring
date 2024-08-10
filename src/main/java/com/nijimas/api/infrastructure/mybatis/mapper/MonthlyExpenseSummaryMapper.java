package com.nijimas.api.infrastructure.mybatis.mapper;

import com.nijimas.api.core.entity.MonthlyExpenseSummaryEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MonthlyExpenseSummaryMapper {

    void insert(MonthlyExpenseSummaryEntity summary);

    void update(MonthlyExpenseSummaryEntity summary);

    MonthlyExpenseSummaryEntity findOne(MonthlyExpenseSummaryEntity summary);
}
