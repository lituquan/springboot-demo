package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    //    @Select("select * from user where id = #{id}")
    User getUserById(Integer id);

    @Select("select * from user where id = #{id}")
    User queryUserById(@Param(value = "id") int id);

    void batchInsert(List<User> users);

    void batchUpdate(List<User> results);

    List<User> getUserByIdIn(List<Integer> ids);

    List<Integer> selectAllId();
}
