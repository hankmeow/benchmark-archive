package com.hankmew.benchmark.spring.threadpool.manager;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ThreadPoolManager {
    // 无返回值的异步任务，多用于上报数据，不影响主流程，注意一定要在本层加try
    // 这里应该是与service一一对应，重点做返回值降级、处理异步
    @Async("defaultTaskExecutor")
    public void report() {
        log.info("启用异步线程上报 {} {}", Thread.currentThread().getName(), System.currentTimeMillis());
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (Exception e) {
            log.error("异步执行失败，不影响主流程");
        }
    }

    // 有返回值的异步任务，多用于并发访问多个资源，并在主流程中聚合，主流程使用线程池的同步手段
    // 这里应该是与service一一对应，重点做返回值降级、处理异步
    @Async("defaultTaskExecutor")
    public CompletableFuture<Integer> callInteger() {
        int i = ThreadLocalRandom.current().nextInt(500);
        log.info("启用异步线程获取Integer {} {} {}", Thread.currentThread().getName(), System.currentTimeMillis(), i);
        try {
            //这里可以调用invoker，可以用try也可以使用状态码模式
            TimeUnit.MILLISECONDS.sleep(i);
        } catch (Exception e) {
            //调用invoker失败则可以在这里返回降级后的数据
            log.error("子线程执行失败");
        }
        return CompletableFuture.completedFuture(i);
    }

    @Async("defaultTaskExecutor")
    public CompletableFuture<Long> callLong() {
        int i = ThreadLocalRandom.current().nextInt(500);
        log.info("启用异步线程获取Long {} {} {}", Thread.currentThread().getName(), System.currentTimeMillis(), i);
        try {
            //这里可以调用invoker，可以用try也可以使用状态码模式
            TimeUnit.MILLISECONDS.sleep(i);
        } catch (Exception e) {
            //调用invoker失败则可以在这里返回降级后的数据
            log.error("子线程执行失败");
        }
        return CompletableFuture.completedFuture((long)i);
    }
}
