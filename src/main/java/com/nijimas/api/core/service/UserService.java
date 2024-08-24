package com.nijimas.api.core.service;

import com.nijimas.api.application.service.user.CreateUserParam;
import com.nijimas.api.application.service.user.UpdateUserParam;
import com.nijimas.api.core.entity.UserEntity;

public interface UserService {
    void registerUser(CreateUserParam createParam);

    void updateUser(UpdateUserParam updateParam);

    UserEntity findByUid(String uid);
}
