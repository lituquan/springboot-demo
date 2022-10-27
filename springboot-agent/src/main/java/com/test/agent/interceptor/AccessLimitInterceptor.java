package com.test.agent.interceptor;

import com.test.agent.annotation.AccessLimit;
import com.test.agent.common.Constant;
import com.test.agent.common.ResponseCode;
import com.test.agent.exception.ServiceException;
import com.test.agent.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * 接口防刷限流拦截器
 */
@Slf4j
public class AccessLimitInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        AccessLimit annotation = method.getAnnotation(AccessLimit.class);
        if (annotation != null) {
            check(annotation, request);
        }

        return true;
    }

    private void check(AccessLimit annotation, HttpServletRequest request) {
        int maxCount = annotation.maxCount();
        int seconds = annotation.seconds();

        StringBuilder sb = new StringBuilder();
        sb.append(Constant.Redis.ACCESS_LIMIT_PREFIX).append(IpUtil.getIpAddress(request)).append(request.getRequestURI());
        String key = sb.toString();

        Object o = redisTemplate.opsForValue().get(key);
        Boolean exists = o != null;
        if (!exists) {
            redisTemplate.opsForValue().set(key, String.valueOf(1), seconds, TimeUnit.SECONDS);
        } else {
            log.info(o.toString());
            Integer count = Integer.valueOf(o.toString());
            if (count < maxCount) {
                Long ttl = redisTemplate.opsForValue().getOperations().getExpire(key);
                if (ttl <= 0) {
                    redisTemplate.opsForValue().set(key, String.valueOf(1), seconds);
                } else {
                    redisTemplate.opsForValue().set(key, String.valueOf(++count), ttl.intValue(), TimeUnit.SECONDS);
                }
            } else {
                throw new ServiceException(ResponseCode.ACCESS_LIMIT.getMsg());
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
