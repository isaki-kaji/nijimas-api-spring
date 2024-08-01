package com.nijimas.api.api.controller;

import com.nijimas.api.TestConfig;
import com.nijimas.api.core.exception.user.UserAlreadyExistsException;
import com.nijimas.api.core.exception.user.UserNotFoundException;
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

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(UserController.class)
@Import(TestConfig.class)
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    String createRequestBody;
    String updateRequestBody;

    @BeforeEach
    void setUp() {
        createRequestBody = """
                {
                "username": "kaji",
                "country_code": "JP"
                }
                """;

        updateRequestBody = """
                {
                "username": "kaji",
                "self_intro": "はじめまして!!"
                }
                """;
    }

    @Test
    @DisplayName("OK: (registerUser)")
    void test_01() throws Exception {

        // given
        doNothing().when(userService).registerUser(any());

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
                .andExpect(jsonPath("$.message").value(containsString("Field 'country_code' must be 2 characters long")))
                .andExpect(jsonPath("$.message").value(containsString("Field 'username' must be between 2 and 14 characters long")));
    }

    @Test
    @DisplayName("OK: (updateUser)")
    void test_06() throws Exception {

        // given
        doNothing().when(userService).updateUser(any());

        // when / then
        mockMvc.perform(
                        put("/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(updateRequestBody)
                )
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("NG: ユーザが存在しない (updateUser)")
    void test_07() throws Exception {

        // given
        var uid = "OKQchGYVq8Z6stnG6XS9YhBqWtZ2";
        doThrow(new UserNotFoundException(uid)).when(userService).updateUser(any());

        // when / then
        mockMvc.perform(
                        put("/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(updateRequestBody)
                )
                .andExpect(status().isNotFound())
                .andExpect(content().json("""
                    {
                        "message": "User with uid OKQchGYVq8Z6stnG6XS9YhBqWtZ2 not found"
                    }
                """));
    }
}
