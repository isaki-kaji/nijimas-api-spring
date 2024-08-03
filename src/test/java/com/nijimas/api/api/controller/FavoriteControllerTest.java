package com.nijimas.api.api.controller;

import com.nijimas.api.TestConfig;
import com.nijimas.api.core.constant.FavoriteStatus;
import com.nijimas.api.core.exception.post.PostNotFoundException;
import com.nijimas.api.core.service.FavoriteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FavoriteController.class)
@Import(TestConfig.class)
public class FavoriteControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    FavoriteService favoriteService;

    String requestBody;

    @BeforeEach
    void setUp() {
        requestBody = """
                {
                "post_id": "9ad11a60-d866-e608-58f9-89e5824f8cc3"
                }
                """;
    }

    @Test
    @DisplayName("OK:「いいね」をする (toggleFavorite)")
    void test_01() throws Exception{

        // given
        doReturn(FavoriteStatus.CREATED).when(favoriteService).toggleFavorite(any());

        // when / then
        mockMvc.perform(
                        post("/favorite")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody)
                )
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("OK:「いいね」を取り消す (toggleFavorite)")
    void test_02() throws Exception{

        // given
        doReturn(FavoriteStatus.DELETED).when(favoriteService).toggleFavorite(any());

        // when / then
        mockMvc.perform(
                        post("/favorite")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody)
                )
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("NG:投稿が存在しない (toggleFavorite)")
    void test_03() throws Exception{

        // given
        UUID postId = UUID.fromString("9ad11a60-d866-e608-58f9-89e5824f8cc3");
        doThrow(new PostNotFoundException(postId)).when(favoriteService).toggleFavorite(any());

        // when / then
        mockMvc.perform(
                        post("/favorite")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody)
                )
                .andExpect(status().isNotFound())
                .andExpect(content().json("""
                    {
                        "message": "Post with id 9ad11a60-d866-e608-58f9-89e5824f8cc3 not"
                    }
                """));
    }
}
