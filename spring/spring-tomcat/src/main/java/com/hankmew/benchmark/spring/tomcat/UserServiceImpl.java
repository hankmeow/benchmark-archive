package com.hankmew.benchmark.spring.tomcat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.groups.Default;

@Slf4j
@Validated(Default.class)
@Service
public class UserServiceImpl implements UserService {
    @Override
    public void add(UserParam user) {
        log.info("add an user name:{} uid:{}", user.getName(), user.getUid());
    }
}
