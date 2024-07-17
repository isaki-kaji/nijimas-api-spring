package com.nijimas.api.core.service;

import com.nijimas.api.application.user.CreateParam;
import com.nijimas.api.application.user.UpdateParam;
import com.nijimas.api.core.entity.UserEntity;

public interface UserService {
    UserEntity createUser(CreateParam createParam);

    void updateUser(UpdateParam updateParam, String ownUid);

    UserEntity findByUid(String uid);
}
