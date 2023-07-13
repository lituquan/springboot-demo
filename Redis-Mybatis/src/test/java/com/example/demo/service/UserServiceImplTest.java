package com.example.demo.service;

import com.example.demo.api.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceImplTest {
    @Autowired
    UserService userService;

    @Test
    void insertUsers() {
        long start = System.currentTimeMillis();
        userService.insertUsers();
        System.out.println(System.currentTimeMillis() - start);
    }
}