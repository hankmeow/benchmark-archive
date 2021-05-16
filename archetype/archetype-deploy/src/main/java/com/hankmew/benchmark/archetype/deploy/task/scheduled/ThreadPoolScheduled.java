package com.hankmew.benchmark.archetype.deploy.task.scheduled;

import com.hankmew.benchmark.archetype.deploy.task.config.TaskThreadPoolProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableAsync
@EnableScheduling
@Component
@Slf4j
public class ThreadPoolScheduled {

    @Async(TaskThreadPoolProperties.TASK1)
    @Scheduled(fixedDelay = 10000, initialDelay = 1000)
    public void scheduled() {

    }
}
