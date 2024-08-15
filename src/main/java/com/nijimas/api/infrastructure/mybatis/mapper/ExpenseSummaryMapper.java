package com.nijimas.api.infrastructure.mybatis.mapper;

import com.nijimas.api.core.dto.summary.CommonSummaryDto;
import com.nijimas.api.core.entity.ExpenseSummaryEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExpenseSummaryMapper {

    void insert(ExpenseSummaryEntity summary);

    void update(ExpenseSummaryEntity summary);

    ExpenseSummaryEntity findOne(ExpenseSummaryEntity summary);

    List<CommonSummaryDto> findByMonth(String uid, Integer year, Integer month);
}
