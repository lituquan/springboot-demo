package com.example.demo.service;

import com.example.demo.api.UserService;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper dao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public User getUser(int userid) {
        User order = (User)redisTemplate.opsForValue().get(userid+"");
        if(order != null){
            return order;
        }

        order = dao.queryUserById(userid);
        if(order != null){
            redisTemplate.opsForValue().set(userid+"", order);
        }
        return order;
    }

    @Override
    public User getUser2(int userid) {
        return dao.getUserById(userid);
    }

    @Cacheable(value = "userStatistics", key = "methodName")
    @Override
    public User getUserById(int id) {
        return dao.queryUserById(id);
    }
}
