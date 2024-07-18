package com.nijimas.api.infrastructure.repository;

import com.nijimas.api.core.entity.SubCategoryEntity;
import com.nijimas.api.core.repository.SubCategoryRepository;
import com.nijimas.api.infrastructure.mybatis.mapper.SubCategoryMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class SubCategoryRepositoryImpl implements SubCategoryRepository {
    private final SubCategoryMapper subCategoryMapper;

    @Override
    public void save(String categoryName) {
        subCategoryMapper.insert(categoryName);
    }

    @Override
    public Optional<SubCategoryEntity> findById(String categoryName) {
        return Optional.ofNullable(subCategoryMapper.findById(categoryName));

    }
}
