package com.test.agent.controller;

import com.test.agent.annotation.Idempotent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/test")
    public String test(){
        return "test";
    }

    @PostMapping("/test")
    @Idempotent
    public String test(@RequestBody String body){
        return "body";
    }
}
