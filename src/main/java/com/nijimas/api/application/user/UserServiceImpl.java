package com.nijimas.api.application.user;

import com.nijimas.api.core.exception.service.UserAlreadyExistsException;
import com.nijimas.api.core.model.User;
import com.nijimas.api.core.repository.UserRepository;
import com.nijimas.api.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

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
            throw new UserAlreadyExistsException();
        });
        User user = new User(createParam.getUid(), createParam.getUsername(), createParam.getCountryCode());
        userRepository.save(user);
        return user;
    }
}
