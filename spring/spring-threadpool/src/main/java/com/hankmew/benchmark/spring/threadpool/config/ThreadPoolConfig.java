package com.hankmew.benchmark.spring.threadpool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池的设置统一在这个地方处理
 * 对于非繁忙的程序，线程池可以有多个
 * 对于繁忙的程序，如果线程池过多，就需要考虑拆分部署
 * 条件允许的话可以考虑增加对每个线程的线程做监控，看等待率和使用率来判断繁忙程度
 */
@EnableAsync
@EnableScheduling
@Configuration
public class ThreadPoolConfig {

    @Bean("defaultTaskScheduler")
    public ThreadPoolTaskScheduler defaultTaskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setThreadNamePrefix("default-");
        //该方法用来设置 线程池关闭 的时候 等待 所有任务都完成后，再继续 销毁 其他的 Bean，这样这些 异步任务 的 销毁 就会先于 数据库连接池对象 的销毁。
        taskScheduler.setWaitForTasksToCompleteOnShutdown(true);
        //该方法用来设置线程池中 任务的等待时间，如果超过这个时间还没有销毁就 强制销毁，以确保应用最后能够被关闭，而不是阻塞住。
        taskScheduler.setAwaitTerminationSeconds(60);
        taskScheduler.setPoolSize(4);
        taskScheduler.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        return taskScheduler;
    }

    @Bean("defaultTaskExecutor")
    public ThreadPoolTaskExecutor defaultTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setThreadNamePrefix("default-");
        //该方法用来设置 线程池关闭 的时候 等待 所有任务都完成后，再继续 销毁 其他的 Bean，这样这些 异步任务 的 销毁 就会先于 数据库连接池对象 的销毁。
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        //该方法用来设置线程池中 任务的等待时间，如果超过这个时间还没有销毁就 强制销毁，以确保应用最后能够被关闭，而不是阻塞住。
        taskExecutor.setAwaitTerminationSeconds(60);
        taskExecutor.setCorePoolSize(4);
        taskExecutor.setMaxPoolSize(4);
        taskExecutor.setKeepAliveSeconds(60);
        taskExecutor.setQueueCapacity(10);
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        return taskExecutor;
    }

}
