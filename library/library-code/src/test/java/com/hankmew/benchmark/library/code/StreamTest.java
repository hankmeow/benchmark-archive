package com.hankmew.benchmark.library.code;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

public class StreamTest {

    @Test
    public void testStream() {
        new HashMap<>();
        ArrayList<User> users = new ArrayList<>();
        users.add(new User(1l, "张三"));
        users.add(new User(2l, "张三"));
        users.add(new User(3l, "李四"));
        Map<String, List<Long>> collect =
            users.stream().collect(Collectors.groupingBy(User::getName, Collectors.mapping(User::getId, Collectors.toList())));
        System.out.println(collect);
    }

    @Data
    public static class User {
        private Long id;
        private String name;

        public User(Long id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
