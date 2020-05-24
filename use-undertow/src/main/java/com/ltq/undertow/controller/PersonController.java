package com.ltq.undertow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
	@Autowired
	Person per;
	
	//RountingInjected 注解会使得HelloController在被加载时hello值使用helloServiceImpl2
	//作用类似Autowired
//	@RountingInjected("helloServiceImpl2")
//	HelloService hello;
	
    @GetMapping("/person/{id}")
    public Person person(@PathVariable(value = "id")int id) {
//    	hello.sayHello();
		return per;
	}
}