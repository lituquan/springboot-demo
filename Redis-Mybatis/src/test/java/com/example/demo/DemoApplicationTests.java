package com.example.demo;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.controller.HelloController;
import com.example.demo.mapper.UserMapper;
import com.example.demo.mapper.UserXmlMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;



@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(basePackages="com.example.demo")
public class DemoApplicationTests {
    private MockMvc mvc;

    @Autowired
    public ApplicationContext app;
    
    @Before
    public void before() {
        mvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
    }

    @Test
    public void test() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/hello").
                accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk()).
                andExpect(content().string(equalTo("hello world!")));

    }
    @Test
    public void test_select() throws Exception {
    	UserMapper mapper = app.getBean(UserMapper.class);
    	System.out.println(mapper.getUserById(1));
    }
    
}
