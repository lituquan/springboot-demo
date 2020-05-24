package com.ltq.undertow.beanpost;

import org.springframework.stereotype.Service;

//@Service
public class HelloServiceImpl2 implements HelloService {
    @Override
    public void sayHello() {
        System.out.println("你好我是HelloServiceImpl2");
    }
	@Override
	public String hello() {
		return "HelloServiceImpl2 : hello world.";
	}
}