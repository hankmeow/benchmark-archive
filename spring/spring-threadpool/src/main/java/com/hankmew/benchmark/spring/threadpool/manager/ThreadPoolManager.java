package com.hankmew.benchmark.spring.threadpool.manager;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ThreadPoolManager {
    // 无返回值的异步任务，多用于上报数据，不影响主流程，注意一定要在本层加try
    @Async("defaultTaskExecutor")
    public void exec() {
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            log.error("异步执行失败，不影响主流程");
        }
    }

    // 有返回值的异步任务，多用于并发访问多个资源，并在主流程中聚合，主流程使用线程池的同步手段
    @Async("defaultTaskExecutor")
    public CompletableFuture<Integer> call() {
        int i = ThreadLocalRandom.current().nextInt(500);
        try {
            TimeUnit.MILLISECONDS.sleep(i);
        } catch (InterruptedException e) {
            log.error("子线程执行失败");
        }
        log.info("{} {}", Thread.currentThread().getName(), i);
        return CompletableFuture.completedFuture(i);
    }
    @Async("defaultTaskExecutor")
    public CompletableFuture<Long> callLong() {
        int i = ThreadLocalRandom.current().nextInt(500);
        try {
            TimeUnit.MILLISECONDS.sleep(i);
        } catch (InterruptedException e) {
            log.error("子线程执行失败");
        }
        log.info("{} {}", Thread.currentThread().getName(), i);
        return CompletableFuture.completedFuture((long)i);
    }
}
