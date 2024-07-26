package com.nijimas.api.infrastructure.repository;

import com.nijimas.api.core.entity.FavoriteEntity;
import com.nijimas.api.core.repository.FavoriteRepository;
import com.nijimas.api.infrastructure.mybatis.mapper.FavoriteMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class FavoriteRepositoryImpl implements FavoriteRepository {
    private final FavoriteMapper favoriteMapper;


    @Override
    public void save(FavoriteEntity favorite) {
        favoriteMapper.insert(favorite);
    }

    @Override
    public boolean existsById(FavoriteEntity favorite) {
        return favoriteMapper.existsById(favorite) != null;
    }

    @Override
    public void delete(FavoriteEntity favorite) {
        favoriteMapper.delete(favorite);
    }
}
