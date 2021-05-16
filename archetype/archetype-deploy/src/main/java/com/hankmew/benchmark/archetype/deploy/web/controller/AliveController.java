package com.hankmew.benchmark.archetype.deploy.web.controller;

import com.hankmew.benchmark.archetype.common.web.result.Result;
import com.hankmew.benchmark.archetype.deploy.web.base.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AliveController {

    @GetMapping("alive")
    public Response alive(Integer a) {
        return Response.make(Result.failure());
    }
}
