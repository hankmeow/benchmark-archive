package com.hankmew.benchmark.spring.tomcat;

import lombok.Data;

@Data
public class UserParamImpl implements UserParam{
    private Long uid;
    private String name;
}
