package com.nijimas.api.core.service;

import com.nijimas.api.application.post.CreateParam;
import com.nijimas.api.core.dto.PostDto;

import java.util.List;

public interface PostService {

    void registerPost(CreateParam param, String ownUid);

    PostDto findById(String postId);

    List<PostDto> findByUid(String uid);
}
