package com.nijimas.api.core.repository;

import com.nijimas.api.core.model.User;

import java.util.Optional;

public interface UserRepository {

    void save(User user);

    Optional<User> findByUid(String uid);
}
