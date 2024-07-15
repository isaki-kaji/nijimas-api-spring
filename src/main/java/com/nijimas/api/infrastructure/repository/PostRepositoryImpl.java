package com.nijimas.api.infrastructure.repository;

import com.nijimas.api.core.dto.PostDto;
import com.nijimas.api.core.model.Post;
import com.nijimas.api.core.repository.PostRepository;
import com.nijimas.api.infrastructure.mybatis.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PostRepositoryImpl implements PostRepository {
    private final PostMapper postMapper;

    @Autowired
    public PostRepositoryImpl(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    @Override
    public void save(Post post) {
        postMapper.insert(post);
    }

    @Override
    public Optional<PostDto> findById(UUID postId) {
        return Optional.ofNullable(postMapper.findById(postId));
    }

    @Override
    public List<PostDto> findByUid(String uid) {
        return postMapper.findByUid(uid);
    }
}
