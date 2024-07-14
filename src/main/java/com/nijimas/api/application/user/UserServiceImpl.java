package com.nijimas.api.application.user;

import com.nijimas.api.core.exception.ForbiddenException;
import com.nijimas.api.core.exception.user.UserAlreadyExistsException;
import com.nijimas.api.core.exception.user.UserNotFoundException;
import com.nijimas.api.core.model.User;
import com.nijimas.api.core.repository.UserRepository;
import com.nijimas.api.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

@Service
@Validated
public class UserServiceImpl implements UserService {

    final private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(CreateParam param) {
        userRepository.findByUid(param.getUid()).ifPresent(user -> {
            throw new UserAlreadyExistsException(user.getUid());
        });

        User user = new User(param);
        userRepository.save(user);
        return user;
    }

    @Override
    public void updateUser(UpdateParam param, String ownUid) {
        if (!Objects.equals(param.getUid(), ownUid)) {
            throw new ForbiddenException("You are not allowed to update this user");
        }
        User user = new User(param);
        userRepository.save(user);
    }

    @Override
    public User findByUid(String uid) {
        return userRepository.findByUid(uid)
                .orElseThrow(() -> new UserNotFoundException(uid));
    }
}
