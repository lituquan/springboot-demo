package com.test.apollo.testapollo.compoent;

import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserPropertiesRefresh implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @ApolloConfigChangeListener(interestedKeyPrefixes = {"user."})
    private void refresh(ConfigChangeEvent changeEvent){
        applicationContext.publishEvent(new EnvironmentChangeEvent(changeEvent.changedKeys()));
        log.info("change key: {}",changeEvent.changedKeys());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}