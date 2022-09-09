package com.example.mongo;

import com.example.mongo.dao.UserRepository;
import com.example.mongo.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.annotation.Resource;

@SpringBootTest
class SpringbootMongoApplicationTests {
    @Resource
    UserRepository userRepository;
    @Test
    void findByName() {
        User user = userRepository.findByName("张三");
        Assert.isTrue(user!=null,"user 不存在");
    }

    @Test
    void saveUser() {
        User byUserId = userRepository.findFirstByUserId(3);
        byUserId.setUserId(3);
        byUserId.setName("李四");
        userRepository.save(byUserId);

        byUserId=new User();
        byUserId.setUserId(4);
        byUserId.setName("王五");
        userRepository.save(byUserId);
    }
}
