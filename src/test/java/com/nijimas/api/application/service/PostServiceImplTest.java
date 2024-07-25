package com.nijimas.api.application.service;

import com.nijimas.api.application.post.CreateParam;
import com.nijimas.api.application.post.PostServiceImpl;
import com.nijimas.api.core.entity.PostEntity;
import com.nijimas.api.core.entity.PostSubcategoryEntity;
import com.nijimas.api.core.repository.PostRepository;
import com.nijimas.api.core.repository.PostSubcategoryRepository;
import com.nijimas.api.core.repository.SubCategoryRepository;
import com.nijimas.api.util.CommonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {

    @InjectMocks
    PostServiceImpl postService;

    @Mock
    PostRepository postRepository;

    @Mock
    SubCategoryRepository subCategoryRepository;

    @Mock
    PostSubcategoryRepository postSubcategoryRepository;

    String ownUid;
    String subCategory1Input;
    CreateParam param;
    ArgumentCaptor<PostEntity> postCaptor;

    @BeforeEach
    void setUp() {
        ownUid = "OKQchGYVq8Z6stnG6XS9YhBqWtZ2";
        subCategory1Input = "イタリアン";
        param = createTestParam();

        postCaptor = ArgumentCaptor.forClass(PostEntity.class);
        doNothing().when(postRepository).save(postCaptor.capture());
    }


    @Test
    @DisplayName("OK:サブカテゴリなし (registerPost)")
    void test_01() {

        // when
        postService.registerPost(param, ownUid);
        var post = postCaptor.getValue();

        // then
        assertPostEntity(post, param);

        verify(postSubcategoryRepository, times(0)).save(any());
    }

    @Test
    @DisplayName("OK:未登録のサブカテゴリが1つ (registerPost)")
    void test_02() {

        // given
        param.setSubCategory1(subCategory1Input);

        doReturn(Optional.empty()).when(subCategoryRepository).findById(any());
        ArgumentCaptor<String> subCategory1Captor = ArgumentCaptor.forClass(String.class);
        doNothing().when(subCategoryRepository).save(subCategory1Captor.capture());

        ArgumentCaptor<PostSubcategoryEntity> pSCategory1Captor = ArgumentCaptor.forClass(PostSubcategoryEntity.class);
        doNothing().when(postSubcategoryRepository).save(pSCategory1Captor.capture());

        // when
        postService.registerPost(param, ownUid);
        var post = postCaptor.getValue();
        var subCategory1 = subCategory1Captor.getValue();
        var pSCategory1 = pSCategory1Captor.getValue();

        // then
        assertPostEntity(post, param);
        assertSubCategory(subCategory1, param, "1");
        assertPostSubcategory(pSCategory1, param, "1");

    }

    //subCategoryが1or2個ある時にhandleSubCategoryメソッドが何回呼ばれたかなどの確認が必要

    private void assertPostEntity(PostEntity post, CreateParam param) {
        assertThat(post).isNotNull();
        assertThat(post.getPostId()).isEqualTo(CommonUtil.parseUuid(param.getPostId()));
        assertThat(post.getUid()).isEqualTo(param.getUid());
        assertThat(post.getMainCategory()).isEqualTo(param.getMainCategory());
        assertThat(post.getExpense()).isEqualTo(param.getExpense());
        assertThat(post.getPostText()).isEqualTo(param.getPostText());
        assertThat(post.getPublicTypeNo()).isEqualTo(param.getPublicTypeNo());
    }

    private void assertSubCategory(String categoryName, CreateParam param, String categoryNo) {
        assertThat(categoryName).isNotNull();

        if (categoryNo.equals("1")) {
            assertThat(categoryName).isEqualTo(param.getSubCategory1());
            return;
        }
        if (categoryNo.equals("2")) {
            assertThat(categoryName).isEqualTo(param.getSubCategory2());
        }
    }

    private void assertPostSubcategory(PostSubcategoryEntity pSCategory, CreateParam param, String categoryNo) {
        assertThat(pSCategory).isNotNull();
        assertThat(pSCategory.getPostId()).isEqualTo(CommonUtil.parseUuid(param.getPostId()));
        if (categoryNo.equals("1")) {
            assertThat(pSCategory.getSubCategory()).isEqualTo(param.getSubCategory1());
            assertThat(pSCategory.getSubcategoryNo()).isEqualTo(categoryNo);
            return;
        }
        if (categoryNo.equals("2")) {
            assertThat(pSCategory.getSubCategory()).isEqualTo(param.getSubCategory2());
            assertThat(pSCategory.getSubcategoryNo()).isEqualTo(categoryNo);
        }
    }

    private CreateParam createTestParam() {
        var param = new CreateParam();
        param.setPostId("9ad11a60-d866-e608-58f9-89e5824f8cc3");
        param.setUid("OKQchGYVq8Z6stnG6XS9YhBqWtZ2");
        param.setMainCategory("food");
        param.setExpense(10000);
        param.setPostText("とてもおいしかったです。");
        param.setPublicTypeNo("1");
        return param;
    }
}
