package com.nijimas.api.core.repository;

import com.nijimas.api.core.dto.PostDto;
import com.nijimas.api.core.entity.PostEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostRepository {

    void save(PostEntity post);

    boolean existsById(UUID postId);

    Optional<PostDto> findById(UUID postId);

    List<PostDto> findByUid(String uid);
}
