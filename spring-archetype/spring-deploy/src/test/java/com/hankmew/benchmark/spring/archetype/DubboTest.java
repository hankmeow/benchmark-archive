package com.hankmew.benchmark.spring.archetype;

import com.hankmew.benchmark.spring.archetype.web.controller.req.UserReq;
import com.hankmew.benchmark.spring.archetype.service.UserService;
import com.hankmew.benchmark.spring.archetype.service.result.UserResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class DubboTest {
    @Reference
    UserService userService;

    @Test
    public void testDubbo() {
        UserReq userReq = new UserReq();
        userReq.setUid(1L);
        UserResult userResult = userService.queryUser(userReq);
        log.info("user result {}", userResult);
    }
}
