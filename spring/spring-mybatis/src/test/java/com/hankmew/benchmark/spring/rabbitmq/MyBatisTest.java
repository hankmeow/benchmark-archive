package com.hankmew.benchmark.spring.rabbitmq;

import com.hankmew.benchmark.spring.rabbitmq.mapper.ValidityMapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class MyBatisTest {
    @Resource
    private ValidityMapper validityMapper;
    @Test
    public void testAmqp() {
        String valid = validityMapper.valid();
        log.info("{}", valid);
    }
}
