package com.nijimas.api.api.controller;

import com.nijimas.api.TestConfig;
import com.nijimas.api.core.entity.UserEntity;
import com.nijimas.api.core.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
@Import(TestConfig.class)
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Test
    @DisplayName("OK: (registerUser)")
    void test_01() throws Exception {

        // given
        UserEntity user = new UserEntity();
        user.setUid("OKQchGYVq8Z6stnG6XS9YhBqWtZ2");
        user.setUsername("kaji");
        user.setCountryCode("JP");
        doReturn(user).when(userService).registerUser(any());

        String requestBody = """
                {
                "uid": "OKQchGYVq8Z6stnG6XS9YhBqWtZ2",
                "username": "kaji",
                "country_code": "JP"
                }
                """;
        // when / then
        mockMvc.perform(
                        post("/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody)
                )
                .andExpect(status().isCreated());
    }
}
