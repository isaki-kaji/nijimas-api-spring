package com.nijimas.api.application.user;

import com.nijimas.api.core.exception.user.UserAlreadyExistsException;
import com.nijimas.api.core.exception.user.UserNotFoundException;
import com.nijimas.api.core.entity.UserEntity;
import com.nijimas.api.core.repository.UserRepository;
import com.nijimas.api.core.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    final private UserRepository userRepository;

    @Override
    public void registerUser(CreateParam param) {
        userRepository.findByUid(param.getUid()).ifPresent(user -> {
            throw new UserAlreadyExistsException(user.getUid());
        });
        userRepository.save(new UserEntity(param));
    }

    @Override
    public void updateUser(UpdateParam param) {
        if (userRepository.findByUid(param.getUid()).isEmpty()) {
            throw new UserNotFoundException(param.getUid());
        }
        UserEntity user = new UserEntity(param);
        userRepository.save(user);
    }

    @Override
    public UserEntity findByUid(String uid) {
        return userRepository.findByUid(uid)
                .orElseThrow(() -> new UserNotFoundException(uid));
    }
}
