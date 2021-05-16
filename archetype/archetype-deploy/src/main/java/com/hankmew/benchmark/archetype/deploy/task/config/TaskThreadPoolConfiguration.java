package com.hankmew.benchmark.archetype.deploy.task.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import javax.annotation.Resource;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * 任务线程池的设置统一在这个地方处理
 * 对于非繁忙的程序，线程池可以有多个
 * 对于繁忙的程序，如果线程池过多，就需要考虑拆分部署
 * 条件允许的话可以考虑增加对每个线程的线程做监控，看等待率和使用率来判断繁忙程度
 */
@EnableScheduling
@Configuration
@Slf4j
public class TaskThreadPoolConfiguration {
    @Resource
    private TaskThreadPoolProperties taskThreadPoolProperties;

    /**
     * 设置定时任务的使用线程池，注意在使用@Scheduled的时候要加上@Async(TaskThreadPoolConfig.TASK1)
     */
    @Bean(TaskThreadPoolProperties.TASK1)
    public ThreadPoolTaskScheduler task1() {
        TaskThreadPoolProperties.Task propertie = taskThreadPoolProperties.getTask().getOrDefault(TaskThreadPoolProperties.TASK1, new TaskThreadPoolProperties.Task());
        ThreadPoolTaskScheduler task = new ThreadPoolTaskScheduler();
        task.setThreadNamePrefix(TaskThreadPoolProperties.TASK1 + "-");
        task.setPoolSize(propertie.getPoolSize());
        task.setWaitForTasksToCompleteOnShutdown(propertie.getAllowWaitTermination());
        task.setAwaitTerminationSeconds(propertie.getAllowWaitTerminationTimeoutSecond());
        task.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        return task;
    }

}
