package com.hankmew.benchmark.spring.archetype.service;

import com.hankmew.benchmark.spring.archetype.service.param.UserParam;
import com.hankmew.benchmark.spring.archetype.service.result.UserResult;

import javax.validation.Valid;

public interface UserService {
    UserResult queryUser(@Valid UserParam userParam);
}
