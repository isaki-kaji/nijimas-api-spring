package com.nijimas.api.infrastructure.mybatis.mapper;

import com.nijimas.api.core.entity.MonthlyExpenseSummaryEntity;
import com.nijimas.api.core.entity.SubCategoryExpenseSummaryEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SubCategoryExpenseSummaryMapper {

    void insert(SubCategoryExpenseSummaryEntity summary);

    void update(SubCategoryExpenseSummaryEntity summary);

    SubCategoryExpenseSummaryEntity findOne(SubCategoryExpenseSummaryEntity summary);
}
