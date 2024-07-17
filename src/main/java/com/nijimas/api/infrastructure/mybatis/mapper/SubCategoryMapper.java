package com.nijimas.api.infrastructure.mybatis.mapper;

import com.nijimas.api.core.entity.SubCategoryEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SubCategoryMapper {

    void insert(String categoryName);

    SubCategoryEntity findById(String categoryName);
}
