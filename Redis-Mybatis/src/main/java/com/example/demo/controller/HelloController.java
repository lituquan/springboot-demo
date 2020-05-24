package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ltq.undertow.beanpost.HelloService;

@Controller
public class HelloController {
	@Reference
	HelloService helloService;
	
    @ResponseBody
    @GetMapping(path = "/hello")
    public String hello() {
        return "hello world!";
    }
    @ResponseBody
    @GetMapping(path = "/consumer")
    public String comsumerHello() {
        return helloService.hello();
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model){
        model.addAttribute("name",name);
        return "greeting";
    }
}
