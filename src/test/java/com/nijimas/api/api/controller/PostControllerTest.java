package com.nijimas.api.api.controller;

import com.nijimas.api.TestConfig;
import com.nijimas.api.core.service.PostService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PostController.class)
@Import(TestConfig.class)
public class PostControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PostService postService;

    String createRequestBody;

    @BeforeEach
    void setUp() {
        createRequestBody = """
                {
                "post_id": "9ad11a60-d866-e608-58f9-89e5824f8cc3",
                "main_category": "food",
                "sub_category1": "中華",
                "sub_category2": "北京ダック",
                "expense": "10000",
                "post_text": "とてもおいしかったです",
                "public_type_no": "1"
                }
                """;
    }

    @Test
    @DisplayName("OK: (registerPost)")
    void test_01() throws Exception{

        // given
        doNothing().when(postService).registerPost(any());

        // when / then
        mockMvc.perform(
                        post("/posts")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(createRequestBody)
                )
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("NG: post_idの値が不正 (registerPost)")
    void test_02() throws Exception{

        // given
        createRequestBody = """
                {
                "post_id": "9ad11a60-d866-e608-58f9-89e5824f8cc31",
                "main_category": "food",
                "sub_category1": "中華",
                "sub_category2": "北京ダック",
                "expense": "10000",
                "post_text": "とてもおいしかったです",
                "public_type_no": "1"
                }
                """;
        doNothing().when(postService).registerPost(any());

        // when / then
        mockMvc.perform(
                        post("/posts")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(createRequestBody)
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().json("""
                            {
                                "message": "Invalid request",
                                "errors": [
                                    {
                                        "source": "post_id",
                                        "message": "post_id must be a valid UUID"
                                    }
                                ]
                            }
                        """));
    }

    @Test
    @DisplayName("NG: main_categoryの値が不正 (registerPost)")
    void test_03() throws Exception{

        // given
        createRequestBody = """
                {
                "post_id": "9ad11a60-d866-e608-58f9-89e5824f8cc3",
                "main_category": "game",
                "sub_category1": "中華",
                "sub_category2": "北京ダック",
                "expense": "10000",
                "post_text": "とてもおいしかったです",
                "public_type_no": "1"
                }
                """;
        doNothing().when(postService).registerPost(any());

        // when / then
        mockMvc.perform(
                        post("/posts")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(createRequestBody)
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().json("""
                            {
                                "message": "Invalid request",
                                "errors": [
                                    {
                                        "source": "main_category",
                                        "message": "main_category must be one of {food,hobbies,fashion,goods,essentials,travel,entertainment,transport,other}"
                                    }
                                ]
                            }
                        """));
    }

    @Test
    @DisplayName("NG: public_type_noの値が不正 (registerPost)")
    void test_04() throws Exception{

        // given
        createRequestBody = """
                {
                "post_id": "9ad11a60-d866-e608-58f9-89e5824f8cc3",
                "main_category": "fashion",
                "sub_category1": "中華",
                "sub_category2": "北京ダック",
                "expense": "10000",
                "post_text": "とてもおいしかったです",
                "public_type_no": "0"
                }
                """;
        doNothing().when(postService).registerPost(any());

        // when / then
        mockMvc.perform(
                        post("/posts")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(createRequestBody)
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().json("""
                            {
                                "message": "Invalid request",
                                "errors": [
                                    {
                                        "source": "public_type_no",
                                        "message": "public_type_no must be one of 1, 2, or 3"
                                    }
                                ]
                            }
                """));;
    }
}
