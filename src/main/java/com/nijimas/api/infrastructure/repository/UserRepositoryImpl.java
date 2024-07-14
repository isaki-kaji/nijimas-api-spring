package com.nijimas.api.infrastructure.repository;

import com.nijimas.api.core.model.User;
import com.nijimas.api.core.repository.UserRepository;
import com.nijimas.api.infrastructure.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final UserMapper userMapper;

    @Autowired
    public UserRepositoryImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void save(User user) {
        if (userMapper.findByUid(user.getUid()) == null) {
            userMapper.insert(user);
            return;
        }
        userMapper.update(user);
    }

    @Override
    public Optional<User> findByUid(String uid) {
        return Optional.ofNullable(userMapper.findByUid(uid));
    }
}
