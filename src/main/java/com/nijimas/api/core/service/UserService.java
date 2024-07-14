package com.nijimas.api.core.service;

import com.nijimas.api.application.user.CreateParam;
import com.nijimas.api.application.user.UpdateParam;
import com.nijimas.api.core.model.User;

public interface UserService {
    User createUser(CreateParam createParam);

    void updateUser(UpdateParam updateParam, String ownUid);

    User findByUid(String uid);
}
