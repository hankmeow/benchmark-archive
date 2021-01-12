package com.hankmew.benchmark.spring.resttemplate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class RestTemplateTest {
    @Resource
    private RestTemplate restTemplate;

    @Test
    public void testRestTemplate() {
        InfoVersionRes res = restTemplate.getForObject("https://api.dnspod.com/Info.Version", InfoVersionRes.class);
        System.out.println(res);
    }

    @Data
    public static class InfoVersionRes {
        private Status status;

        @Data
        public static class Status {
            private String code;
            private String message;
            @JsonProperty("created_at")
            private String createdAt;
        }
    }
}
