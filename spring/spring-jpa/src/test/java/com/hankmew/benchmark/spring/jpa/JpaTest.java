package com.hankmew.benchmark.spring.jpa;

import com.hankmew.benchmark.spring.jpa.dao.AccountDao;
import com.hankmew.benchmark.spring.jpa.entity.AccountEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest
public class JpaTest {
    @Resource
    private AccountDao accountDao;

    @Test
    public void testJpa() {
        Iterable<AccountEntity> all = accountDao.findAll();
        all.forEach(item -> {
            log.info("{}", item.getName());
        });
    }
}
