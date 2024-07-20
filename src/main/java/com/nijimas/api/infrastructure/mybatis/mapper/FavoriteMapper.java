package com.nijimas.api.infrastructure.mybatis.mapper;

import com.nijimas.api.core.entity.FavoriteEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.UUID;

@Mapper
public interface FavoriteMapper {

    void insert(FavoriteEntity favorite);

    void delete(FavoriteEntity favorite);

    FavoriteEntity find(@Param("postId") UUID postId, @Param("uid") String uid);
}
