package com.test.apollo.testapollo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Lazy
public class TestController {

    @Value( "${hello}" )
    String dateValue;

    @GetMapping("test")
    public String test() {
        return "打印配置中心的 dateValue 值: "+ dateValue;
    }

}
