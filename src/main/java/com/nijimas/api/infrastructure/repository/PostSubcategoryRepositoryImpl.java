package com.nijimas.api.infrastructure.repository;

import com.nijimas.api.core.entity.PostSubcategoryEntity;
import com.nijimas.api.core.repository.PostSubcategoryRepository;
import com.nijimas.api.infrastructure.mybatis.mapper.PostSubcategoryMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class PostSubcategoryRepositoryImpl implements PostSubcategoryRepository {
    private final PostSubcategoryMapper postSubcategoryMapper;

    @Override
    public void save(PostSubcategoryEntity postSubcategory) {
        postSubcategoryMapper.insert(postSubcategory);
    }
}
