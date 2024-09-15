package com.nijimas.api.infrastructure.mybatis.mapper;

import com.nijimas.api.core.entity.SubCategoryEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SubCategoryMapper {

    void insert(SubCategoryEntity subCategoryEntity);

    SubCategoryEntity findById(String categoryName);
}
