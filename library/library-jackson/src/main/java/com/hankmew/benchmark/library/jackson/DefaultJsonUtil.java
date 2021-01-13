package com.hankmew.benchmark.library.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DefaultJsonUtil {
    private final static ObjectMapper objectMapper;

    //如果有自定义的json配置，可以继承本类，并重写static，同时可以完全复用本类的所有方法
    static {
        objectMapper = new ObjectMapper();
    }

    public static String writeValueAsString(Object o)  {
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            //这里可以抛出自定义的业务异常
            throw new RuntimeException();
        }
    }
}
