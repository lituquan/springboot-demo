package com.test.agent.aspect;

import com.test.agent.annotation.Idempotent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author jeckxu
 */
@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class IdempotentAspect {
    private ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal();
    private static final String RMAPCACHE_KEY = "magical:idempotent";
    private static final String KEY = "key";
    private static final String DELKEY = "delKey";
    private final RedissonClient redissonClient;
    private final KeyResolver keyResolver;


    @Pointcut("@annotation(com.test.agent.annotation.Idempotent)")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void beforePointCut(JoinPoint joinPoint) throws Exception {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        if (!method.isAnnotationPresent(Idempotent.class)) {
            return;
        }
        Idempotent idempotent = method.getAnnotation(Idempotent.class);
        String key;
        // 若没有配置 幂等 标识编号，则使用 url + 参数列表作为区分
        if (StringUtils.isEmpty(idempotent.key())) {
            String url = request.getRequestURL().toString();
            String argString = Arrays.asList(joinPoint.getArgs()).toString();
            key = url + argString;
        } else {
            // 使用jstl 规则区分
            key = keyResolver.resolver(idempotent, joinPoint);
            log.info("check key:{}", key);
        }
        long expireTime = idempotent.expireTime();
        String info = idempotent.info();
        TimeUnit timeUnit = idempotent.timeUnit();
        boolean delKey = idempotent.delKey();
        // do not need check null
        RMapCache<String, Object> rMapCache = redissonClient.getMapCache(RMAPCACHE_KEY);
        String value = LocalDateTime.now().toString().replace("T", " ");
        Object v1;
        if (null != rMapCache.get(key)) {
            // had stored
            throw new RuntimeException(info);
        }
        synchronized (this) {
            v1 = rMapCache.putIfAbsent(key, value, expireTime, TimeUnit.SECONDS);
            if (null != v1) {
                throw new RuntimeException(info);
            } else {
                log.info("[idempotent]:has stored key={},value={},expireTime={}{},now={}", key, value, expireTime, timeUnit, LocalDateTime.now().toString());
            }
        }
        Map<String, Object> map = CollectionUtils.isEmpty(threadLocal.get()) ? new HashMap<>(4) : threadLocal.get();
        map.put(KEY, key);
        map.put(DELKEY, delKey);
        threadLocal.set(map);
    }

    @After("pointCut()")
    public void afterPointCut(JoinPoint joinPoint) {
        Map<String, Object> map = threadLocal.get();
        if (CollectionUtils.isEmpty(map)) {
            return;
        }
        RMapCache<Object, Object> mapCache = redissonClient.getMapCache(RMAPCACHE_KEY);
        if (mapCache.size() == 0) {
            return;
        }
        String key = map.get(KEY).toString();
        boolean delKey = (boolean) map.get(DELKEY);
        if (delKey) {
            mapCache.fastRemove(key);
            log.info("[idempotent]:has removed key={}", key);
        }
        threadLocal.remove();
    }
}