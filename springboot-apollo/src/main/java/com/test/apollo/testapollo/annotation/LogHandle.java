package com.test.apollo.testapollo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogHandle {
    String value() default "";

    Type type() default Type.ALL;

    enum Type {
        ALL,
        INPUT,
        OUTPUT
    }

}
