package com.example.demo.service;

import com.example.demo.api.UserService;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUser(int userid) {
        User order = (User) redisTemplate.opsForValue().get(userid + "");
        if (order != null) {
            return order;
        }

        order = userMapper.queryUserById(userid);
        if (order != null) {
            redisTemplate.opsForValue().set(userid + "", order);
        }
        return order;
    }

    @Override
    public User getUser2(int userid) {
        return userMapper.getUserById(userid);
    }

    @Cacheable(value = "userStatistics", key = "methodName")
    @Override
    public User getUserById(int id) {
        return userMapper.queryUserById(id);
    }

    @Override
    public void insertUsers() {
        long start = System.currentTimeMillis();
        List<User> userList = new ArrayList<>();
        User user;
        for (int i = 0; i < 10000; i++) {
            user = new User();
            user.setName("name" + i);
            userList.add(user);
        }
//        saveBatch(userList, 1000);
        long end = System.currentTimeMillis();
        System.out.println("一万条数据总耗时：" + (end - start) + "ms");
    }
}
