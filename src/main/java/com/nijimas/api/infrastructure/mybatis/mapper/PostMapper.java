package com.nijimas.api.infrastructure.mybatis.mapper;

import com.nijimas.api.core.dto.PostDto;
import com.nijimas.api.core.entity.PostEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.UUID;

@Mapper
public interface PostMapper {

    void insert(PostEntity post);

    Integer existsById(UUID postId);

    PostDto findById(UUID postId);

    List<PostDto> findOwn(String uid);
}
