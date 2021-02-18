package com.hankmew.benchmark.spring.archetype.web.controller.req;

import com.hankmew.benchmark.spring.archetype.service.param.UserParam;
import lombok.Data;

@Data
public class UserReq implements UserParam {
    private Long uid;
}
