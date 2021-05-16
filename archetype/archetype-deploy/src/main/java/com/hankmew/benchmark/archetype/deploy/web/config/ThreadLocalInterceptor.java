package com.hankmew.benchmark.archetype.deploy.web.config;

import com.hankmew.benchmark.archetype.common.web.util.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
@Slf4j
public class ThreadLocalInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        ThreadLocalUtil.put("", "");
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                       @Nullable Exception ex) throws Exception {
        //这里一定要remove掉，否则会内存泄露
        ThreadLocalUtil.remove();
    }

}
