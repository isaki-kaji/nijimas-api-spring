package com.nijimas.api.application.user;

import com.nijimas.api.exception.UserAlreadyExistsException;
import com.nijimas.api.exception.UserNotFoundException;
import com.nijimas.api.core.model.User;
import com.nijimas.api.core.repository.UserRepository;
import com.nijimas.api.core.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.InvalidPropertyException;
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
    public User createUser(@Valid CreateParam createParam) {
        userRepository.findByUid(createParam.getUid()).ifPresent(user -> {
            throw new UserAlreadyExistsException(user.getUid());
        });
        User user = new User(createParam.getUid(), createParam.getUsername(), createParam.getCountryCode());
        userRepository.save(user);
        return user;
    }

    @Override
    public User findByUid(String uid) {
        return userRepository.findByUid(uid)
                .orElseThrow(() -> {
                    return new UserNotFoundException(uid);
                });
    }
}
