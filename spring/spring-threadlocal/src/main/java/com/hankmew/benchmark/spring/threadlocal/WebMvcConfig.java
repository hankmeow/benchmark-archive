package com.hankmew.benchmark.spring.threadlocal;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Component
public class WebMvcConfig implements WebMvcConfigurer {
    @Resource
    private ThreadLocalInterceptor threadLocalInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(threadLocalInterceptor).addPathPatterns("/**");
    }
}
