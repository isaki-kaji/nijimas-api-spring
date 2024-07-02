package com.nijimas.api.core.user;

import java.util.Optional;

public interface UserRepository {

    void save(User user);

    Optional<User> findByUid(String uid);
}
