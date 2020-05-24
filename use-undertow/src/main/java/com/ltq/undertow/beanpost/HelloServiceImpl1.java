package com.ltq.undertow.beanpost;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;

@Service
@Component
public class HelloServiceImpl1 implements HelloService {
    @Override
    public void sayHello() {
        System.out.println("你好我是HelloServiceImpl1");
    }

	@Override
	public String hello() {
		return "HelloServiceImpl1 : hello world.";
	}
}
//com.ltq.undertow.beanpost