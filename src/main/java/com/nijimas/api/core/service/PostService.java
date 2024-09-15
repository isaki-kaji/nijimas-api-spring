package com.nijimas.api.core.service;

import com.nijimas.api.application.service.post.CreatePostParam;
import com.nijimas.api.core.dto.PostDto;

import java.util.List;

public interface PostService {

    void registerPost(CreatePostParam param);

    PostDto findById(String postId);

    List<PostDto> findOwn(String uid);
}
