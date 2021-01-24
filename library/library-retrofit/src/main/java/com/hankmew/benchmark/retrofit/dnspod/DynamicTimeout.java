package com.hankmew.benchmark.retrofit.dnspod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DynamicTimeout {
    int connectTimeout() default 10000;
    int readTimeout() default 10000;
    int writeTimeout() default 10000;
    int maxRetry() default 0;
}
