package com.hankmew.benchmark.spring.archetype.web.util;

public class ResultWrap<T> {
    private Integer code;
    private String msg;
    private T data;
}
