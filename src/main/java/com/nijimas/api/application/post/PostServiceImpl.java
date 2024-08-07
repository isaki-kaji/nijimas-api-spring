package com.nijimas.api.application.post;

import com.nijimas.api.core.dto.PostDto;
import com.nijimas.api.core.entity.PostEntity;
import com.nijimas.api.core.entity.PostSubcategoryEntity;
import com.nijimas.api.core.repository.PostRepository;
import com.nijimas.api.core.repository.PostSubcategoryRepository;
import com.nijimas.api.core.repository.SubCategoryRepository;
import com.nijimas.api.core.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final PostSubcategoryRepository postSubcategoryRepository;

    @Override
    @Transactional
    public void registerPost(CreateParam param) {

        PostEntity post = new PostEntity(param);
        postRepository.save(post);

        // subCategory2のみ設定されていた場合はsubCategory2がsubCategory2として登録される
//        if (param.getSubCategory1() == null && param.getSubCategory2() != null){
//            handleSubCategory(post.getPostId(), param.getSubCategory2(), "1");
//            return;
//        }
        handleSubCategory(post.getPostId(), param.getSubCategory1(), "1");
        handleSubCategory(post.getPostId(), param.getSubCategory2(), "2");
    }

    @Override
    public PostDto findById(String postId) {
        return null;
    }

    @Override
    public List<PostDto> findByUid(String uid) {
        return postRepository.findByUid(uid);
    }

    private void handleSubCategory(UUID postId, String subCategoryName, String subCategoryNo) {
        if (subCategoryName == null) {
            return;
        }

        if (subCategoryRepository.findById(subCategoryName).isEmpty()) {
            subCategoryRepository.save(subCategoryName);
        }

        PostSubcategoryEntity postSubcategory = new PostSubcategoryEntity(postId, subCategoryName, subCategoryNo);
        postSubcategoryRepository.save(postSubcategory);
    }
}
