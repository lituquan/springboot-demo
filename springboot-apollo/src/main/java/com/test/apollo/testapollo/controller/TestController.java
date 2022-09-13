package com.test.apollo.testapollo.controller;

import com.test.apollo.testapollo.annotation.LogHandle;
import com.test.apollo.testapollo.config.AppConfig;
import com.test.apollo.testapollo.config.RefreshConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@Slf4j
@RestController
@Lazy
public class TestController {

    @Value( "${hello}" )
    String dateValue;

    @Resource
    AppConfig appConfig;

    @Resource
    RefreshConfig refreshConfig;

    @GetMapping("/test")
    @LogHandle
    public String test() {
        return "打印配置中心的 dateValue 值: "+ dateValue;
    }

    @GetMapping("/user")
    @LogHandle("获取用户")
    public String user() {
        return "name:"+appConfig.getName()+",id:"+appConfig.getId();
    }

    @GetMapping("/user/list")
    public String userList() {
        String s = appConfig.getList().stream().map(user -> user.getId() + ":" + user.getName()).reduce((x, y) -> x + "<br/>" + y).get();
        return s;
    }

    @GetMapping("/user/map")
    @ResponseBody
    @LogHandle(value = "配置map", type = LogHandle.Type.INPUT)
    public Map userMap() {
        Map<String, String> maps = appConfig.getMaps();
        return maps;
    }

    @GetMapping("/refreshConfig")
    @LogHandle(value = "刷新配置", type = LogHandle.Type.OUTPUT)
    public String refreshConfig() {
        return refreshConfig.getRefresh();
    }
}
