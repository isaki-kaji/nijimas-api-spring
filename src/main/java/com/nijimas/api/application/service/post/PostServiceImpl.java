package com.nijimas.api.application.service.post;

import com.nijimas.api.core.dto.PostDto;
import com.nijimas.api.core.entity.PostEntity;
import com.nijimas.api.core.entity.PostSubcategoryEntity;
import com.nijimas.api.core.entity.SubCategoryEntity;
import com.nijimas.api.core.repository.PostRepository;
import com.nijimas.api.core.repository.PostSubcategoryRepository;
import com.nijimas.api.core.repository.SubCategoryRepository;
import com.nijimas.api.core.service.PostService;
import com.nijimas.api.core.service.SummaryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final PostSubcategoryRepository postSubcategoryRepository;
    private final SummaryService summaryService;

    @Override
    @Transactional
    public void registerPost(CreatePostParam param) {

        PostEntity post = new PostEntity(param);
        postRepository.save(post);

        handleSubCategory(post.getPostId(), param.getSubCategory1(), "1");
        handleSubCategory(post.getPostId(), param.getSubCategory2(), "2");

        summaryService.execute(param);
    }

    @Override
    public PostDto findById(String postId) {
        return null;
    }

    @Override
    public List<PostDto> findOwn(String uid) {
        return postRepository.findOwn(uid);
    }

    private void handleSubCategory(UUID postId, String subCategoryName, String subCategoryNo) {
        if (subCategoryName == null || subCategoryName.isEmpty()) {
            return;
        }

        UUID subCategoryId = subCategoryRepository.findById(subCategoryName)
                .map(SubCategoryEntity::getCategoryId)
                .orElseGet(() -> createAndSaveNewSubCategory(subCategoryName));

        savePostSubcategory(postId, subCategoryId, subCategoryNo);
    }

    private UUID createAndSaveNewSubCategory(String subCategoryName) {
        SubCategoryEntity newSubCategory = new SubCategoryEntity(subCategoryName);
        subCategoryRepository.save(newSubCategory);
        return newSubCategory.getCategoryId();
    }

    private void savePostSubcategory(UUID postId, UUID subCategoryId, String subCategoryNo) {
        PostSubcategoryEntity postSubcategory = new PostSubcategoryEntity(postId, subCategoryId, subCategoryNo);
        postSubcategoryRepository.save(postSubcategory);
    }

}
