package com.hankmew.benchmark.spring.archetype.service.impl;

import com.hankmew.benchmark.spring.archetype.service.UserService;
import com.hankmew.benchmark.spring.archetype.service.result.UserResult;
import com.hankmew.benchmark.spring.archetype.service.param.UserParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
@Slf4j
@org.apache.dubbo.config.annotation.Service
public class UserServiceImpl implements UserService {
    @Override
    public UserResult queryUser(@Valid UserParam userParam) {
        UserResult userResult = new UserResult();
        userResult.setUid(userParam.getUid());
        return userResult;
    }
}
