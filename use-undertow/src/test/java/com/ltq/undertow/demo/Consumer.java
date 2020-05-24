package com.ltq.undertow.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ltq.undertow.beanpost.HelloService;

//https://www.cnblogs.com/baijinqiang/p/10848259.html
/*
 * 
 * 
	服务发布
	接口+服务实现+spring装配+服务暴露+服务注册zk
	
	服务消费
	接口+zk+服务代理对象生成+服务调用

 * 
 * */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Consumer {
    @Reference//dubbo注解，可以仔细了解下这个注解
    private HelloService helloService;

	@Test
	public void consum() {
		String hello = helloService.hello();
		System.out.println(hello);
	}
	
}
