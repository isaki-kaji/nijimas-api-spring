package com.nijimas.api.infrastructure.repository;

import com.nijimas.api.core.entity.UserEntity;
import com.nijimas.api.core.repository.UserRepository;
import com.nijimas.api.infrastructure.mybatis.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserMapper userMapper;

    @Override
    public void save(UserEntity user) {
        if (userMapper.findByUid(user.getUid()) == null) {
            userMapper.insert(user);
            return;
        }
        userMapper.update(user);
    }

    @Override
    public Optional<UserEntity> findByUid(String uid) {
        return Optional.ofNullable(userMapper.findByUid(uid));
    }
}
