package com.test.apollo.testapollo.aspect;

import com.insnail.base.common.util.Json;
import com.test.apollo.testapollo.annotation.Ignore;
import com.test.apollo.testapollo.annotation.LogHandle;
import com.test.apollo.testapollo.annotation.LogHandle.Type;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
public class LogHandleAspect {
    private static final Map<String, Logger> loggerMap = new ConcurrentHashMap<>();

    private static final LogHandle DEFAULT_LOG_HANDLER = new LogHandle() {
        public Class<? extends Annotation> annotationType() {
            return LogHandle.class;
        }

        public String value() {
            return "";
        }

        public LogHandle.Type type() {
            return Type.ALL;
        }
    };

    @Pointcut("(@annotation(com.test.apollo.testapollo.annotation.LogHandle) || @within(com.test.apollo.testapollo.annotation.LogHandle))" +
            "&& !@annotation(com.test.apollo.testapollo.annotation.Ignore) && !@within(com.test.apollo.testapollo.annotation.Ignore)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        // 优先获取方法上的注解
        LogHandle LogHandle = Optional.ofNullable(methodSignature.getMethod().getAnnotation(LogHandle.class))
                .orElseGet(() -> joinPoint.getTarget().getClass().getAnnotation(LogHandle.class));
        if (LogHandle == null) {
            LogHandle = DEFAULT_LOG_HANDLER;
        }
        String signature = LogHandle.value().equals("")
                ? methodSignature.toShortString()
                : LogHandle.value();
        Logger log = getLog(joinPoint.getTarget().getClass());
        long start = System.currentTimeMillis();
        Type type = LogHandle.type();
        if (Type.ALL.equals(type)
                || Type.INPUT.equals(type)) {
            Object[] args = joinPoint.getArgs();
            Annotation[][] annotations = methodSignature.getMethod().getParameterAnnotations();
            String[] names = methodSignature.getParameterNames();
            Map<String, Object> map = new LinkedHashMap<>();
            for (int i = 0; i < args.length; i++) {
                Object arg = args[i];
                String name = names[i];
                if (isPrintable(arg, annotations[i])) {
                    map.put(name, arg);
                }
            }
            logInput(log, signature, start, map);
        }
        Object result = joinPoint.proceed();
        if (Type.ALL.equals(type)
                || Type.OUTPUT.equals(type)) {
            logOutput(log, signature, start, result);
        }
        return result;
    }

    private Logger getLog(Class<?> clazz) {
        return loggerMap.computeIfAbsent(clazz.getName(), k -> LoggerFactory.getLogger(clazz));
    }

    private boolean isPrintable(Object obj) {
        return isPrintable(obj, null);
    }

    private boolean isPrintable(Object obj, Annotation[] annotations) {
        if (obj == null)
            return false;
        Class<?> aClass = obj.getClass();
        if (Objects.nonNull(annotations)) {
            for (Annotation annotation : annotations) {
                if (annotation instanceof Ignore) {
                    return false;
                }
                if (annotation instanceof LogHandle) {
                    return true;
                }
            }
        }
        if (aClass.isPrimitive()) return true;
        Package aPackage = aClass.getPackage();
        if (Objects.isNull(aPackage))
            return false;
        String name = aPackage.getName();
        return name.startsWith("com.insnail")
                || name.startsWith("java.lang")
                || name.startsWith("java.util");
    }

    private void logInput(Logger log, String signature, long start, Map<String, Object> map) {
        try {
            log.info("{}|{} input: {}", signature, start, Json.stringify(map));
        } catch (Exception e) {
            log.warn("{}|{} input log failed, reason: {}", signature, start, e.getMessage());
        }
    }

    private void logOutput(Logger log, String signature, long start, Object object) {
        long end = System.currentTimeMillis();
        long spend = end - start;
        try {
            log.info("{}|{} [{}ms] output: {}", signature, start, spend, isPrintable(object) ? Json.stringify(object) : null);
        } catch (Exception e) {
            log.warn("{}|{} [{}ms] output log failed, reason: {}", signature, start, spend, e.getMessage());
        }
    }
}
