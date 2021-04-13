package com.hankmew.benchmark.spring.threadpool.service;

import com.hankmew.benchmark.spring.threadpool.manager.ThreadPoolManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ThreadPoolService {
    @Resource
    private ThreadPoolManager threadPoolManager;

    /**
     * 定时任务
     */
    public void scheduled() {
        //实际业务，如同步配置到内存中
        //打印一下
        log.info("定时任务 {} {}", Thread.currentThread().getName(), System.currentTimeMillis());
    }

    /**
     * 异步上报
     */
    public void modify() {
        //这里可以增加业务代码
        log.info("业务代码完成 {} {}", Thread.currentThread().getName(), System.currentTimeMillis());
        //使用线程池异步上报业务代码中的数据
        threadPoolManager.report();
    }

    /**
     * 并发访问依赖资源
     */
    public void query() {
        log.info("业务代码完成 {} {}", Thread.currentThread().getName(), System.currentTimeMillis());
        //  并发调用多个相同返回值的异步方法
        List<CompletableFuture<Integer>> integerTasks = new ArrayList<>();
        //这里的调用不耗时
        integerTasks.add(threadPoolManager.callInteger());
        integerTasks.add(threadPoolManager.callInteger());
        //这里子线程实际开始耗时调用，主线程在这里阻塞直到最后一个子线程返回
        List<Integer> integerList = integerTasks.stream().map(CompletableFuture::join).collect(Collectors.toList());

        //  并发调用多个不相同返回值的异步方法
        //这里的调用不耗时
        CompletableFuture<Integer> integerTask = threadPoolManager.callInteger();
        CompletableFuture<Long> longTask = threadPoolManager.callLong();
        CompletableFuture<Void> completableFuture = CompletableFuture.allOf(integerTask, longTask);
        try {
            //这里子线程实际开始耗时调用，主线程在这里阻塞直到最后一个子线程返回
            completableFuture.join();
            Integer integerResult = integerTask.get();
            Long longResult = longTask.get();
        } catch (Exception e) {
            log.error("线程调用异常", e);
        }
    }
}
