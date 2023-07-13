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
public class UserDataBatchImpl extends DataBatch<User, User> {
    @Autowired
    UserMapper mapper;

    @Override
    public int getBatchSize() {
        return 100000;
    }

    @Override
    public void save(List<User> results) {
        mapper.batchInsert(results);
    }

    @Override
    public List<User> handle(List<User> intData) {
        return intData;
    }
}
