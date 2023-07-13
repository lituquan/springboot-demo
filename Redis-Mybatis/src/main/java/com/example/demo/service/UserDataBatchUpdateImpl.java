package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Author: lituquan
 * Date: 2023/6/26
 */
@Component
public class UserDataBatchUpdateImpl extends DataBatch<Integer, User> {
    @Autowired
    UserMapper mapper;

    @Override
    public int getBatchSize() {
        return 1000;
    }

    @Override
    public void save(List<User> results) {
        mapper.batchUpdate(results);
    }

    @Override
    public List<User> handle(List<Integer> intData) {
        List<User> users = mapper.getUserByIdIn(intData);
        users.stream().forEach(user -> user.setAge(user.getAge() + 1));
        return users;
    }
}
