package com.hankmew.benchmark.spring.redistemplate;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
public class RedisTemplateTest {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testRedisTemplate() {
        String s = stringRedisTemplate.opsForValue().get("a");
        System.out.println(s);
    }
}
