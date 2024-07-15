package com.nijimas.api.infrastructure.mybatis.mapper;

import com.nijimas.api.core.model.SubCategory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SubCategoryMapper {

    void insert(String categoryName);

    SubCategory findById(String categoryName);
}
