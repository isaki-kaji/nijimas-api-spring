package com.nijimas.api.api.controller;

import com.nijimas.api.TestConfig;
import com.nijimas.api.core.entity.UserEntity;
import com.nijimas.api.core.exception.user.UserAlreadyExistsException;
import com.nijimas.api.core.service.UserService;
import org.junit.jupiter.api.BeforeEach;
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
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
@Import(TestConfig.class)
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    String createRequestBody;
    UserEntity user;

    @BeforeEach
    void setUp() {
        createRequestBody = """
                {
                "username": "kaji",
                "country_code": "JP"
                }
                """;

        user = new UserEntity();
        user.setUid("OKQchGYVq8Z6stnG6XS9YhBqWtZ2");
        user.setUsername("kaji");
        user.setCountryCode("JP");
    }

    @Test
    @DisplayName("OK: (registerUser)")
    void test_01() throws Exception {

        // given
        doReturn(user).when(userService).registerUser(any());

        // when / then
        mockMvc.perform(
                        post("/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(createRequestBody)
                )
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("NG: ユーザがすでに存在している (registerUser)")
    void test_02() throws Exception {

        // given
        doThrow(UserAlreadyExistsException.class).when(userService).registerUser(any());

        // when / then
        mockMvc.perform(
                        post("/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(createRequestBody)
                )
                .andExpect(status().isConflict());
    }

    @Test
    @DisplayName("NG: リクエストのusernameの値が不正 (createUser)")
    void test_03() throws Exception {

        // given
        createRequestBody = """
                {
                "username": "kkkkkkkkkkkkkkk",
                "country_code": "JP"
                }
                """;

        // when / then
        mockMvc.perform(
                        post("/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(createRequestBody)
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("""
                    {
                        "message": "Field 'username' must be between 2 and 14 characters long (rejected value: kkkkkkkkkkkkkkk)"
                    }
                """));
    }

    @Test
    @DisplayName("NG: リクエストのcountry_codeの値が不正 (createUser)")
    void test_04() throws Exception {

        // given
        createRequestBody = """
                {
                "username": "kaji",
                "country_code": "JPN"
                }
                """;

        // when / then
        mockMvc.perform(
                        post("/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(createRequestBody)
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("""
                    {
                        "message": "Field 'country_code' must be 2 characters long (rejected value: JPN)"
                    }
                """));
    }

    @Test
    @DisplayName("NG: リクエストのusernameとcountry_codeの値が不正 (createUser)")
    void test_05() throws Exception {

        // given
        createRequestBody = """
                {
                "username": "kkkkkkkkkkkkkkk",
                "country_code": "JPN"
                }
                """;

        // when / then
        mockMvc.perform(
                        post("/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(createRequestBody)
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("""
                    {
                        "message": "Field 'username' must be between 2 and 14 characters long (rejected value: kkkkkkkkkkkkkkk) , Field 'country_code' must be 2 characters long (rejected value: JPN)"
                    }
                """));
    }
}
