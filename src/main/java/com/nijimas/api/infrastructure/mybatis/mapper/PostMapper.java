package com.nijimas.api.infrastructure.mybatis.mapper;

import com.nijimas.api.core.dto.PostDto;
import com.nijimas.api.core.model.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.UUID;

@Mapper
public interface PostMapper {

    void insert(Post post);

    PostDto findById(UUID postId);

    List<PostDto> findByUid(String uid);
}
