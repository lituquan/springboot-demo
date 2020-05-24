package com.ltq.undertow.beanpost;

import java.util.Map;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;

public class RoutingBeanProxyFactory {

    private final static String DEFAULT_BEAN_NAME = "helloServiceImpl1";

    public static Object createProxy(String name, Class type, Map<String, Object> candidates) {
        if("".equals(name)) {
        	name=DEFAULT_BEAN_NAME;
        }
    	ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setInterfaces(type);
        proxyFactory.addAdvice(new VersionRoutingMethodInterceptor(name, candidates));
        return proxyFactory.getProxy();
    }

    static class VersionRoutingMethodInterceptor implements MethodInterceptor {
        private Object targetObject;

        //查找设定的bean对象
        public VersionRoutingMethodInterceptor(String name, Map<String, Object> beans) {
            this.targetObject = beans.get(name);
            if (this.targetObject == null) {
                this.targetObject = beans.get(DEFAULT_BEAN_NAME);
            }
        }

        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            return invocation.getMethod().invoke(this.targetObject, invocation.getArguments());
        }
    }
}