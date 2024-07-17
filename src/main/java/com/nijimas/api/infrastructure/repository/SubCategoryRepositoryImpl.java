package com.nijimas.api.infrastructure.repository;

import com.nijimas.api.core.entity.SubCategoryEntity;
import com.nijimas.api.core.repository.SubCategoryRepository;
import com.nijimas.api.infrastructure.mybatis.mapper.SubCategoryMapper;

import java.util.Optional;

public class SubCategoryRepositoryImpl implements SubCategoryRepository {
    private final SubCategoryMapper subCategoryMapper;

    public SubCategoryRepositoryImpl(SubCategoryMapper subCategoryMapper) {
        this.subCategoryMapper = subCategoryMapper;
    }


    @Override
    public void save(String categoryName) {
        subCategoryMapper.insert(categoryName);
    }

    @Override
    public Optional<SubCategoryEntity> findById(String categoryName) {
        return Optional.ofNullable(subCategoryMapper.findById(categoryName));

    }
}
