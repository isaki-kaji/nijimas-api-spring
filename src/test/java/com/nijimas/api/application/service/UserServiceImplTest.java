package com.nijimas.api.application.service;

import com.nijimas.api.application.service.user.CreateUserParam;
import com.nijimas.api.application.service.user.UpdateUserParam;
import com.nijimas.api.application.service.user.UserServiceImpl;
import com.nijimas.api.core.entity.UserEntity;
import com.nijimas.api.core.exception.user.UserAlreadyExistsException;
import com.nijimas.api.core.exception.user.UserNotFoundException;
import com.nijimas.api.core.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Test
    @DisplayName("OK (registerUser)")
    void test_01() {

        // given
        var param = createTestCreateParam();

        // when
        doReturn(Optional.empty()).when(userRepository).findByUid(any());

        var userCaptor = ArgumentCaptor.forClass(UserEntity.class);
        doNothing().when(userRepository).save(userCaptor.capture());

        userService.registerUser(param);

        // then
        var user = userCaptor.getValue();
        assertThat(user).isNotNull();
        assertThat(user.getUid()).isEqualTo(param.getUid());
        assertThat(user.getUsername()).isEqualTo(param.getUsername());
        assertThat(user.getCountryCode()).isEqualTo(param.getCountryCode());
    }

    @Test
    @DisplayName("NG:ユーザがすでに存在している (registerUser)")
    void test_02() {

        // given
        var param = createTestCreateParam();
        var existingUser = new UserEntity(param);

        doReturn(Optional.of(existingUser)).when(userRepository).findByUid(any());

        // when / then
        assertThatThrownBy(() -> userService.registerUser(param))
                .isInstanceOf(UserAlreadyExistsException.class)
                .hasMessageContaining(existingUser.getUid());
    }

    @Test
    @DisplayName("OK (updateUser)")
    void test_03() {

        //given
        var param = createTestUpdateParam();
        var existingUser = new UserEntity(param);

        doReturn(Optional.of(existingUser)).when(userRepository).findByUid(any());

        // when
        var userCaptor = ArgumentCaptor.forClass(UserEntity.class);
        doNothing().when(userRepository).save(userCaptor.capture());

        userService.updateUser(param);

        // then
        var user = userCaptor.getValue();
        assertThat(user).isNotNull();
        assertThat(user.getUid()).isEqualTo(param.getUid());
        assertThat(user.getUsername()).isEqualTo(param.getUsername());
        assertThat(user.getSelfIntro()).isEqualTo(param.getSelfIntro());
    }

    @Test
    @DisplayName("NG:ユーザが存在しない (updateUser)")
    void test_04() {

        //given
        var param = createTestUpdateParam();

        doReturn(Optional.empty()).when(userRepository).findByUid(any());

        // when / then
        assertThatThrownBy(() -> userService.updateUser(param))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessageContaining(param.getUid());
    }

    private CreateUserParam createTestCreateParam() {
        var param = new CreateUserParam();
        param.setUid("OKQchGYVq8Z6stnG6XS9YhBqWtZ2");
        param.setUsername("kaji");
        param.setCountryCode("JP");
        return param;
    }

    private UpdateUserParam createTestUpdateParam() {
        var param = new UpdateUserParam();
        param.setUid("OKQchGYVq8Z6stnG6XS9YhBqWtZ2");
        param.setUsername("isaki");
        param.setSelfIntro("はじめまして");
        return param;
    }
}
