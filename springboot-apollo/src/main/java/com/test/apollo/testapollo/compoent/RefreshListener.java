package com.test.apollo.testapollo.compoent;

import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.scope.refresh.RefreshScope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class RefreshListener {

    @Resource
    RefreshScope refreshScope;

    @ApolloConfigChangeListener(interestedKeyPrefixes = {"test"})
    private void refresh(ConfigChangeEvent changeEvent){
        refreshScope.refresh("refreshConfig");
    }
}
