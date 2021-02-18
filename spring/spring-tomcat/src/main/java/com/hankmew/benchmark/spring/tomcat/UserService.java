package com.hankmew.benchmark.spring.tomcat;

import javax.validation.Valid;

public interface UserService {
    void add(@Valid UserParam user);
}
