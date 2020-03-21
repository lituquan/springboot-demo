package com.example.demo.controller;

import com.example.demo.api.UserService;
import com.example.demo.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
public class UserController {
    
    @GetMapping("/getuser")
    public User getUser() {
        User user = new User();
        user.setName("test");
        return user;
    }
    @Autowired
    private UserService userService;

    @GetMapping("/query")
    public Object queryById(int id){
        return userService.getUser(id);
    }
    @GetMapping("/query2")
    public Object queryById2(int id){
        return userService.getUser(id);
    }

    @ApiOperation(value = "查询用户",notes = "",httpMethod = "GET")
    @ApiImplicitParam(dataType = "string",name = "id",value = "用户id",required = true)
    @GetMapping("/query3")
    public Object queryById3(int id){
        return userService.getUser(id);
    }

}
