package com.nijimas.api.core.service;

import com.nijimas.api.application.user.CreateParam;
import com.nijimas.api.core.model.User;

public interface UserService {
    User createUser(CreateParam createParam);

    User findByUid(String uid);
}
