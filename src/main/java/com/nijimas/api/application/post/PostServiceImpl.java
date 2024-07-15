package com.nijimas.api.application.post;

import com.nijimas.api.core.dto.PostDto;
import com.nijimas.api.core.model.Post;
import com.nijimas.api.core.model.PostSubcategory;
import com.nijimas.api.core.repository.PostRepository;
import com.nijimas.api.core.repository.PostSubcategoryRepository;
import com.nijimas.api.core.repository.SubCategoryRepository;
import com.nijimas.api.core.service.PostService;
import com.nijimas.api.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final PostSubcategoryRepository postSubcategoryRepository;

    @Autowired
    public PostServiceImpl(
            PostRepository postRepository,
            SubCategoryRepository subCategoryRepository,
            PostSubcategoryRepository postSubcategoryRepository) {
        this.postRepository = postRepository;
        this.subCategoryRepository = subCategoryRepository;
        this.postSubcategoryRepository = postSubcategoryRepository;
    }

    @Override
    @Transactional
    public void post(CreateParam param, String ownUid) {
        UserUtil.checkUid(param.getUid(), ownUid);

        Post post = new Post(param);
        postRepository.save(post);

        handleSubCategory(post.getPostId(), param.getSubCategory1(),"1");
        handleSubCategory(post.getPostId(), param.getSubCategory2(),"2");
    }

    @Override
    public PostDto findById(String postId) {
        return null;
    }

    @Override
    public List<PostDto> findByUid(String uid) {
        return null;
    }

    private void handleSubCategory(UUID postId, String subCategoryNo, String subCategoryName) {
        if (subCategoryName == null) {
            return;
        }

        if (subCategoryRepository.findById(subCategoryName).isEmpty()) {
            subCategoryRepository.save(subCategoryName);
        }

        PostSubcategory postSubcategory = new PostSubcategory(postId, subCategoryNo, subCategoryName);
        postSubcategoryRepository.save(postSubcategory);
    }
}
