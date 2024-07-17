package com.nijimas.api.infrastructure.mybatis.mapper;

import com.nijimas.api.core.entity.PostSubcategoryEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostSubcategoryMapper {

    void insert(PostSubcategoryEntity postSubcategory);
}
