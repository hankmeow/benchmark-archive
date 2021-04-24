package com.hankmew.benchmark.spring.threadlocal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class ThreadLocalInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        ThreadLocalUtil.put("a", "aaa");
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                       @Nullable Exception ex) throws Exception {
        log.info("{}", ThreadLocalUtil.get("a", String.class).orElse("bbb"));
        //这里一定要remove掉，否则会内存泄露
        ThreadLocalUtil.remove();
    }

}
