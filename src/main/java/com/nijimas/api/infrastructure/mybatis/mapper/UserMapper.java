package com.nijimas.api.infrastructure.mybatis.mapper;

import com.nijimas.api.core.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    void insert(User user);

    User findByUid(@Param("uid") String uid);

    //void update(@Param("user") User user);
}
