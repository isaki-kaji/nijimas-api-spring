package com.nijimas.api.infrastructure.repository;

import com.nijimas.api.core.dto.PostDto;
import com.nijimas.api.core.entity.PostEntity;
import com.nijimas.api.core.repository.PostRepository;
import com.nijimas.api.infrastructure.mybatis.mapper.PostMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class PostRepositoryImpl implements PostRepository {
    private final PostMapper postMapper;

    @Override
    public void save(PostEntity post) {
        postMapper.insert(post);
    }

    @Override
    public boolean existsById(UUID postId) {
        return postMapper.existsById(postId) != null;
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
