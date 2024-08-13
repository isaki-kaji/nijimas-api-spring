package com.nijimas.api.infrastructure.mybatis.mapper;

import com.nijimas.api.core.entity.ExpenseSummaryEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExpenseSummaryMapper {

    void insert(ExpenseSummaryEntity summary);

    void update(ExpenseSummaryEntity summary);

    ExpenseSummaryEntity findOne(ExpenseSummaryEntity summary);
}
