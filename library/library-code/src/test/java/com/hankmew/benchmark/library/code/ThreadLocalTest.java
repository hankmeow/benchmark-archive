package com.hankmew.benchmark.library.code;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class ThreadLocalTest {
    @SneakyThrows
    @Test
    public void testThreadLocal() {
        ThreadLocal<String> tl = new ThreadLocal<>();
        tl.set("abc");

        System.gc();

        TimeUnit.SECONDS.sleep(15);

        System.out.println(tl.get());

    }
}
