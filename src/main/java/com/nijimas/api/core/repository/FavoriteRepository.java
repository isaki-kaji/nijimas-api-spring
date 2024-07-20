package com.nijimas.api.core.repository;

import com.nijimas.api.core.entity.FavoriteEntity;

import java.util.Optional;
import java.util.UUID;

public interface FavoriteRepository {

    void save(FavoriteEntity favorite);

    void delete(FavoriteEntity favorite);

    Optional<FavoriteEntity> find(UUID postId, String uid);
}
