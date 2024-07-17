package com.nijimas.api.application.user;

import com.nijimas.api.core.exception.user.UserAlreadyExistsException;
import com.nijimas.api.core.exception.user.UserNotFoundException;
import com.nijimas.api.core.entity.UserEntity;
import com.nijimas.api.core.repository.UserRepository;
import com.nijimas.api.core.service.UserService;
import com.nijimas.api.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class UserServiceImpl implements UserService {

    final private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity createUser(CreateParam param) {
        userRepository.findByUid(param.getUid()).ifPresent(user -> {
            throw new UserAlreadyExistsException(user.getUid());
        });

        UserEntity user = new UserEntity(param);
        userRepository.save(user);
        return user;
    }

    @Override
    public void updateUser(UpdateParam param, String ownUid) {
        UserUtil.checkUid(param.getUid(), ownUid);
        UserEntity user = new UserEntity(param);
        userRepository.save(user);
    }

    @Override
    public UserEntity findByUid(String uid) {
        return userRepository.findByUid(uid)
                .orElseThrow(() -> new UserNotFoundException(uid));
    }
}
