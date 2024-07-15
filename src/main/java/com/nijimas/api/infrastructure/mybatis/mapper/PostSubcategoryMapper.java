package com.nijimas.api.infrastructure.mybatis.mapper;

import com.nijimas.api.core.model.PostSubcategory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostSubcategoryMapper {

    void insert(PostSubcategory postSubcategory);
}
