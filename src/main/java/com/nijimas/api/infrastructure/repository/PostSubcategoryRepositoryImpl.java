package com.nijimas.api.infrastructure.repository;

import com.nijimas.api.core.model.PostSubcategory;
import com.nijimas.api.core.repository.PostSubcategoryRepository;
import com.nijimas.api.infrastructure.mybatis.mapper.PostSubcategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class PostSubcategoryRepositoryImpl implements PostSubcategoryRepository {
    private final PostSubcategoryMapper postSubcategoryMapper;

    @Autowired
    public PostSubcategoryRepositoryImpl(PostSubcategoryMapper postSubcategoryMapper) {
        this.postSubcategoryMapper = postSubcategoryMapper;
    }

    @Override
    public void save(PostSubcategory postSubcategory) {
        postSubcategoryMapper.insert(postSubcategory);
    }
}