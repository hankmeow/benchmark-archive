package com.hankmew.benchmark.spring.threadpool;

import com.hankmew.benchmark.spring.threadpool.service.ThreadPoolService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest
public class ThreadPoolTest {
    @Resource
    private ThreadPoolService threadPoolService;

    @Test
    public void testModify() {
        threadPoolService.modify();
    }
    @Test
    public void testQuery() {
        threadPoolService.query();
    }
}
