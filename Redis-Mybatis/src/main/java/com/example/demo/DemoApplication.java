package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;

@SpringBootApplication(scanBasePackages = "com.example.demo")
@EnableDubboConfiguration
public class DemoApplication {

    public static void main(String[] args) {
        System.out.println("start");
        SpringApplication.run(DemoApplication.class, args);
    }

}
