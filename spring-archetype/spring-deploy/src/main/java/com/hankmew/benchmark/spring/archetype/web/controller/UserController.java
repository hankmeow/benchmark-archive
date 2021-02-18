package com.hankmew.benchmark.spring.archetype.web.controller;

import com.hankmew.benchmark.spring.archetype.web.controller.res.UserRes;
import com.hankmew.benchmark.spring.archetype.service.UserService;
import com.hankmew.benchmark.spring.archetype.service.result.UserResult;
import com.hankmew.benchmark.spring.archetype.web.controller.req.UserReq;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/user/query")
    public UserRes queryUser(UserReq req) {
        UserResult userResult = userService.queryUser(req);
        return userResult;
    }
}
