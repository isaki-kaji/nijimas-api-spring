package com.nijimas.api.infrastructure.mybatis.mapper;

import com.nijimas.api.core.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface UserMapper {

    void insert(@Param("user") User user);

    User findByUid(@Param("uid") String uid);

    void update(@Param("user") User user);
}
