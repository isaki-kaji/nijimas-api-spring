package com.nijimas.api.infrastructure.mybatis.mapper;

import com.nijimas.api.core.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    void insert(UserEntity user);

    UserEntity findByUid(String uid);

    void update(UserEntity user);
}
