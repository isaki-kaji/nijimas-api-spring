package com.nijimas.api.application.service;

import com.nijimas.api.application.favorite.FavoriteServiceImpl;
import com.nijimas.api.application.favorite.ToggleParam;
import com.nijimas.api.core.constant.FavoriteStatus;
import com.nijimas.api.core.entity.FavoriteEntity;
import com.nijimas.api.core.exception.post.PostNotFoundException;
import com.nijimas.api.core.repository.FavoriteRepository;
import com.nijimas.api.core.repository.PostRepository;
import com.nijimas.api.util.CommonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FavoriteServiceImplTest {

    @InjectMocks
    FavoriteServiceImpl favoriteService;

    @Mock
    FavoriteRepository favoriteRepository;

    @Mock
    PostRepository postRepository;

    ToggleParam param;
    ArgumentCaptor<FavoriteEntity> favoriteCaptor;

    @BeforeEach
    void setUp() {
        param = createTestParam();

        favoriteCaptor = ArgumentCaptor.forClass(FavoriteEntity.class);
    }

    @Test
    @DisplayName("OK:「いいね」をする (toggleFavorite)")
    void test_01() {

        // given
        doReturn(true).when(postRepository).existsById(any());
        doReturn(false).when(favoriteRepository).existsById(any());
        doNothing().when(favoriteRepository).save(favoriteCaptor.capture());

        // when
        var favoriteStatus = favoriteService.toggleFavorite(param);
        var favorite = favoriteCaptor.getValue();

        // then
        assertThat(favoriteStatus).isEqualTo(FavoriteStatus.CREATED);
        assertFavorite(favorite, param);
    }

    @Test
    @DisplayName("OK:「いいね」を取り消す (toggleFavorite)")
    void test_02() {

        // given
        doReturn(true).when(postRepository).existsById(any());
        doReturn(true).when(favoriteRepository).existsById(any());
        doNothing().when(favoriteRepository).delete(favoriteCaptor.capture());

        // when
        var favoriteStatus = favoriteService.toggleFavorite(param);
        var favorite = favoriteCaptor.getValue();

        // then
        assertThat(favoriteStatus).isEqualTo(FavoriteStatus.DELETED);
        assertFavorite(favorite, param);
    }

    @Test
    @DisplayName("NG: 投稿が存在しない (toggleFavorite)")
    void test_03() {

        // given
        doReturn(false).when(postRepository).existsById(any());

        // when / then
        assertThatThrownBy(() -> favoriteService.toggleFavorite(param))
                .isInstanceOf(PostNotFoundException.class)
                .hasMessageContaining(param.getPostId());

        verify(favoriteRepository, times(0)).save(any());
        verify(favoriteRepository, times(0)).delete(any());
    }

    private void assertFavorite(FavoriteEntity favorite, ToggleParam param) {
        assertThat(favorite).isNotNull();
        assertThat(favorite.getPostId()).isEqualTo(CommonUtil.parseUuid(param.getPostId()));
        assertThat(favorite.getUid()).isEqualTo(param.getUid());
    }

    private ToggleParam createTestParam() {
        var param = new ToggleParam();
        param.setPostId("9ad11a60-d866-e608-58f9-89e5824f8cc3");
        param.setUid("OKQchGYVq8Z6stnG6XS9YhBqWtZ2");
        return param;
    }
}
