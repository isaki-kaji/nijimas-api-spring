package com.nijimas.api.core.service;

import com.nijimas.api.application.user.CreateParam;
import com.nijimas.api.application.user.UpdateParam;
import com.nijimas.api.core.entity.UserEntity;

public interface UserService {
    UserEntity registerUser(CreateParam createParam);

    void updateUser(UpdateParam updateParam);

    UserEntity findByUid(String uid);
}
