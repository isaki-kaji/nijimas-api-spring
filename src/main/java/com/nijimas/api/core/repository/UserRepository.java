package com.nijimas.api.core.repository;

import com.nijimas.api.core.entity.UserEntity;

import java.util.Optional;

public interface UserRepository {

    void save(UserEntity user);

    Optional<UserEntity> findByUid(String uid);
}
