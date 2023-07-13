package com.example.demo;

import com.example.demo.config.DataBatchWithSpring;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserDataBatchImpl;
import com.example.demo.service.UserDataBatchUpdateImpl;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.stream.IntStream;


@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(basePackages = "com.example.demo")
@Slf4j
public class DemoApplicationTests {
    private static final int MAX = 50000;
    private MockMvc mvc;

    @Autowired
    public ApplicationContext app;
    private UserMapper mapper;
    @Autowired
    private UserDataBatchImpl dataBatch;
    @Autowired
    Executor executor;
    @Autowired
    DataBatchWithSpring dataBatchWithSpring;
    @Autowired
    private UserDataBatchUpdateImpl dataBatchUpdate;

    @Before
    public void init() {
        mapper = app.getBean(UserMapper.class);
    }

    @Test
    public void test_select() throws Exception {
        System.out.println(mapper.getUserById(1));
    }

    @Test
    public void testInsert() throws Exception {
        List<User> users = new LinkedList<>();
        Random random = new Random(System.currentTimeMillis());
        IntStream.range(0, MAX).forEach(i -> {
            User user = new User();
            user.setName(random.nextInt() + "");
            user.setAge(random.nextInt());
            users.add(user);
        });
        mapper = app.getBean(UserMapper.class);
        long start = System.currentTimeMillis();
        dataBatch.syncDataFromDc(executor, users);
        System.out.println(System.currentTimeMillis() - start);
    }

    @SneakyThrows
    @Test
    public void test() {
        dataBatchWithSpring.run();
    }

    @SneakyThrows
    @Test
    public void testUpdate() {
        List<Integer> ids = mapper.selectAllId();
        long start = System.currentTimeMillis();
        dataBatchUpdate.syncDataFromDc(executor, ids);
        System.out.println(System.currentTimeMillis() - start);
    }
}
