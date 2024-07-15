package com.nijimas.api.core.repository;

import com.nijimas.api.core.dto.PostDto;
import com.nijimas.api.core.model.Post;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostRepository {

    void save(Post post);

    Optional<PostDto> findById(UUID postId);

    List<PostDto> findByUid(String uid);
}
