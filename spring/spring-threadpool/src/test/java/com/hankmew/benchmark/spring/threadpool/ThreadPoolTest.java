package com.hankmew.benchmark.spring.threadpool;

import com.hankmew.benchmark.spring.threadpool.manager.ThreadPoolManager;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class ThreadPoolTest {
    @Resource
    private ThreadPoolManager manager;
    @Test
    public void testCall() {
        List<CompletableFuture<Integer>> list = new ArrayList<>();
        long start = System.currentTimeMillis();
        list.add(manager.call());
        list.add(manager.call());
        log.info("add {}", System.currentTimeMillis() - start);
        start = System.currentTimeMillis();
        List<Integer> collect = list.stream().map(CompletableFuture::join).collect(Collectors.toList());
        log.info("end {}", System.currentTimeMillis() - start);
    }
    @Test
    public void testCall2() {
        CompletableFuture<Integer> call = manager.call();
        CompletableFuture<Long> call2 = manager.callLong();
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(call, call2);
        long start = System.currentTimeMillis();
        voidCompletableFuture.join();
        log.info("{}", System.currentTimeMillis() - start);
        try {

            Integer integer = call.get();
            Long aLong = call2.get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
