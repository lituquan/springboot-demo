package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.Cacheable;

@Mapper
public interface UserMapper {

    @Select("select * from user where id = #{id}")
    @Cacheable(cacheNames = "demoCache", key = "#id")
    public User getUserById(Integer id);

    @Select("select * from user where id = #{id}")
    User queryUserById(@Param(value = "id") int id);
}
