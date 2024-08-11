package com.nijimas.api.application.service;

import com.nijimas.api.application.post.CreatePostParam;
import com.nijimas.api.application.post.PostServiceImpl;
import com.nijimas.api.core.entity.PostEntity;
import com.nijimas.api.core.entity.PostSubcategoryEntity;
import com.nijimas.api.core.entity.SubCategoryEntity;
import com.nijimas.api.core.repository.PostRepository;
import com.nijimas.api.core.repository.PostSubcategoryRepository;
import com.nijimas.api.core.repository.SubCategoryRepository;
import com.nijimas.api.core.service.SummaryService;
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

    @Mock
    SummaryService summaryService;

    String ownUid;
    String subCategory1Input;
    String subCategory2Input;
    CreatePostParam param;
    SubCategoryEntity existingSubCategory;
    ArgumentCaptor<PostEntity> postCaptor;
    ArgumentCaptor<String> subCategoryCaptor;
    ArgumentCaptor<PostSubcategoryEntity> postSubCategoryCaptor;

    @BeforeEach
    void setUp() {
        ownUid = "OKQchGYVq8Z6stnG6XS9YhBqWtZ2";
        subCategory1Input = "イタリアン";
        subCategory2Input = "ミシュラン一つ星";
        existingSubCategory = new SubCategoryEntity(subCategory1Input);
        param = createTestParam();

        postCaptor = ArgumentCaptor.forClass(PostEntity.class);
        subCategoryCaptor = ArgumentCaptor.forClass(String.class);
        postSubCategoryCaptor = ArgumentCaptor.forClass(PostSubcategoryEntity.class);

        doNothing().when(postRepository).save(postCaptor.capture());
        doNothing().when(summaryService).execute(any());
    }


    @Test
    @DisplayName("OK:サブカテゴリなし (registerPost)")
    void test_01() {

        // when
        postService.registerPost(param);
        var post = postCaptor.getValue();

        // then
        assertPost(post, param);

        verify(postSubcategoryRepository, times(0)).save(any());
    }

    @Test
    @DisplayName("OK:初出のサブカテゴリが1つ (registerPost)")
    void test_02() {

        // given
        param.setSubCategory1(subCategory1Input);

        doReturn(Optional.empty()).when(subCategoryRepository).findById(any());
        doNothing().when(subCategoryRepository).save(subCategoryCaptor.capture());

        ArgumentCaptor<PostSubcategoryEntity> pSCategory1Captor = ArgumentCaptor.forClass(PostSubcategoryEntity.class);
        doNothing().when(postSubcategoryRepository).save(pSCategory1Captor.capture());

        // when
        postService.registerPost(param);
        var post = postCaptor.getValue();
        var subCategory1 = subCategoryCaptor.getValue();
        var pSCategory1 = pSCategory1Captor.getValue();

        // then
        assertPost(post, param);
        assertSubCategory(subCategory1, param, "1");
        assertPostSubcategory(pSCategory1, param, "1");
    }

    @Test
    @DisplayName("OK:初出のサブカテゴリが2つ (registerPost)")
    void test_03() {

        // given
        param.setSubCategory1(subCategory1Input);
        param.setSubCategory2(subCategory2Input);

        doReturn(Optional.empty()).when(subCategoryRepository).findById(any());

        // when
        postService.registerPost(param);
        var post = postCaptor.getValue();

        ArgumentCaptor<String> subCategoryCaptor = ArgumentCaptor.forClass(String.class);
        verify(subCategoryRepository, times(2)).save(subCategoryCaptor.capture());

        ArgumentCaptor<PostSubcategoryEntity> pSCategoryCaptor = ArgumentCaptor.forClass(PostSubcategoryEntity.class);
        verify(postSubcategoryRepository, times(2)).save(pSCategoryCaptor.capture());

        var subCategory1 = subCategoryCaptor.getAllValues().get(0);
        var subCategory2 = subCategoryCaptor.getAllValues().get(1);
        var pSCategory1 = pSCategoryCaptor.getAllValues().get(0);
        var pSCategory2 = pSCategoryCaptor.getAllValues().get(1);

        // then
        assertPost(post, param);
        assertSubCategory(subCategory1, param, "1");
        assertSubCategory(subCategory2, param, "2");
        assertPostSubcategory(pSCategory1, param, "1");
        assertPostSubcategory(pSCategory2, param, "2");
    }

    @Test
    @DisplayName("OK:サブカテゴリが2つ_サブカテゴリ1が既出_サブカテゴリ2が初出 (registerPost)")
    void test_04() {

        // given
        param.setSubCategory1(subCategory1Input);
        param.setSubCategory2(subCategory2Input);

        doReturn(Optional.of(existingSubCategory), Optional.empty())
                .when(subCategoryRepository).findById(any());

        ArgumentCaptor<String> subCategoryCaptor = ArgumentCaptor.forClass(String.class);
        doNothing().when(subCategoryRepository).save(subCategoryCaptor.capture());

        // when
        postService.registerPost(param);
        var post = postCaptor.getValue();

        ArgumentCaptor<PostSubcategoryEntity> pSCategoryCaptor = ArgumentCaptor.forClass(PostSubcategoryEntity.class);
        verify(postSubcategoryRepository, times(2)).save(pSCategoryCaptor.capture());

        var subCategory2 = subCategoryCaptor.getValue();
        var pSCategory1 = pSCategoryCaptor.getAllValues().get(0);
        var pSCategory2 = pSCategoryCaptor.getAllValues().get(1);

        // then
        assertPost(post, param);
        assertSubCategory(subCategory2, param, "2");
        assertPostSubcategory(pSCategory1, param, "1");
        assertPostSubcategory(pSCategory2, param, "2");

        verify(subCategoryRepository, times(1)).save(any());
    }


    private void assertPost(PostEntity post, CreatePostParam param) {
        assertThat(post).isNotNull();
        assertThat(post.getPostId()).isEqualTo(CommonUtil.parseUuid(param.getPostId()));
        assertThat(post.getUid()).isEqualTo(param.getUid());
        assertThat(post.getMainCategory()).isEqualTo(param.getMainCategory());
        assertThat(post.getExpense()).isEqualTo(param.getExpense());
        assertThat(post.getPostText()).isEqualTo(param.getPostText());
        assertThat(post.getPublicTypeNo()).isEqualTo(param.getPublicTypeNo());
    }

    private void assertSubCategory(String categoryName, CreatePostParam param, String categoryNo) {
        assertThat(categoryName).isNotNull();

        if (categoryNo.equals("1")) {
            assertThat(categoryName).isEqualTo(param.getSubCategory1());
            return;
        }
        if (categoryNo.equals("2")) {
            assertThat(categoryName).isEqualTo(param.getSubCategory2());
        }
    }

    private void assertPostSubcategory(PostSubcategoryEntity pSCategory, CreatePostParam param, String categoryNo) {
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

    private CreatePostParam createTestParam() {
        var param = new CreatePostParam();
        param.setPostId("9ad11a60-d866-e608-58f9-89e5824f8cc3");
        param.setUid("OKQchGYVq8Z6stnG6XS9YhBqWtZ2");
        param.setMainCategory("food");
        param.setExpense(10000);
        param.setPostText("とてもおいしかったです。");
        param.setPublicTypeNo("1");
        return param;
    }
}
