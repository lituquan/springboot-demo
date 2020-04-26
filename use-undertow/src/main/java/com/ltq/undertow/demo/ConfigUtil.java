package com.ltq.undertow.demo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ConfigUtil implements InitializingBean, ApplicationContextAware {
    @Value("${test.hello}")
    public String test;

    public static String hello;

    @Override
    public void afterPropertiesSet() throws Exception {
        // hello = test;/* */
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        hello = test;
    }

}