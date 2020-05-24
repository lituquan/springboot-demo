package com.ltq.undertow.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;

@SpringBootApplication
@EnableDubboConfiguration	
@ComponentScan(basePackages = { "com.ltq.undertow.beanpost","com.ltq.undertow.controller"})
public class DemoApplication {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(DemoApplication.class, args);
//        Person bean = ctx.getBean(Person.class);
//        System.out.println(bean); 
    }

}
