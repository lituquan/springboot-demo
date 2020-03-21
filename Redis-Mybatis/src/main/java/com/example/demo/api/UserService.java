package com.example.demo.api;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserService {
    public User getUser(@Param("userid")  int userid);
    public User getUser2(@Param("userid")  int userid);
    public User getUserById(@Param("userid")  int userid);

}