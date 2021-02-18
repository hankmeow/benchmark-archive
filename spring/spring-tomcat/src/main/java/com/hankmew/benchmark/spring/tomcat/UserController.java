package com.hankmew.benchmark.spring.tomcat;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/user/add")
    public String addUser(@RequestParam String name,
                          @RequestParam Long uid) {
        UserParamImpl userParam = new UserParamImpl();
        userParam.setName(name);
        userParam.setUid(uid);
        userService.add(userParam);
        return "ok";
    }
}
