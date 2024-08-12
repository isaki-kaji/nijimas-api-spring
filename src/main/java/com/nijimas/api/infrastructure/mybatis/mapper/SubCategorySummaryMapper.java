package com.nijimas.api.infrastructure.mybatis.mapper;

import com.nijimas.api.core.entity.SubCategorySummaryEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SubCategorySummaryMapper {

    void insert(SubCategorySummaryEntity summary);

    void update(SubCategorySummaryEntity summary);

    SubCategorySummaryEntity findOne(SubCategorySummaryEntity summary);
}
