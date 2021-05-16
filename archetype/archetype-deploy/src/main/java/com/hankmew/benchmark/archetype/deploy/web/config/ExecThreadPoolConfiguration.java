package com.hankmew.benchmark.archetype.deploy.web.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 异步线程池的设置统一在这个地方处理
 * 对于非繁忙的程序，线程池可以有多个
 * 对于繁忙的程序，如果线程池过多，就需要考虑拆分部署
 * 条件允许的话可以考虑增加对每个线程的线程做监控，看等待率和使用率来判断繁忙程度
 */
@EnableAsync
@Configuration
@Slf4j
public class ExecThreadPoolConfiguration {
    @Resource
    private ExecThreadPoolProperties execThreadPoolProperties;

    /**
     * 设置线程池，注意在使用的时候要加上@Async(ExecThreadPoolConfig.EXEC1)
     */
    @Bean(ExecThreadPoolProperties.EXEC1)
    public ThreadPoolTaskExecutor exec1() {
        ExecThreadPoolProperties.Exec propertie = execThreadPoolProperties.getExecution().getOrDefault(ExecThreadPoolProperties.EXEC1, new ExecThreadPoolProperties.Exec());
        ThreadPoolTaskExecutor exec = new ThreadPoolTaskExecutor();
        exec.setThreadNamePrefix(ExecThreadPoolProperties.EXEC1 + "-");
        exec.setCorePoolSize(propertie.getCoreSize());
        exec.setQueueCapacity(propertie.getQueueCapacity());
        exec.setMaxPoolSize(propertie.getMaxSize());
        exec.setAllowCoreThreadTimeOut(propertie.getAllowCoreThreadTimeout());
        exec.setKeepAliveSeconds(propertie.getKeepAliveSecond());
        exec.setWaitForTasksToCompleteOnShutdown(propertie.getAllowWaitTermination());
        exec.setAwaitTerminationSeconds(propertie.getAllowWaitTerminationTimeoutSecond());
        exec.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        return exec;
    }

    /**
     * 设置线程池，注意在使用的时候要加上@Async(ExecThreadPoolConfig.EXEC2)
     */
    @Bean(ExecThreadPoolProperties.EXEC2)
    public ThreadPoolTaskExecutor exec2() {
        ExecThreadPoolProperties.Exec propertie = execThreadPoolProperties.getExecution().getOrDefault(ExecThreadPoolProperties.EXEC2, new ExecThreadPoolProperties.Exec());
        ThreadPoolTaskExecutor exec = new ThreadPoolTaskExecutor();
        exec.setThreadNamePrefix(ExecThreadPoolProperties.EXEC2 + "-");
        exec.setCorePoolSize(propertie.getCoreSize());
        exec.setQueueCapacity(propertie.getQueueCapacity());
        exec.setMaxPoolSize(propertie.getMaxSize());
        exec.setAllowCoreThreadTimeOut(propertie.getAllowCoreThreadTimeout());
        exec.setKeepAliveSeconds(propertie.getKeepAliveSecond());
        exec.setWaitForTasksToCompleteOnShutdown(propertie.getAllowWaitTermination());
        exec.setAwaitTerminationSeconds(propertie.getAllowWaitTerminationTimeoutSecond());
        exec.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        return exec;
    }
}
