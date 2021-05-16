package com.hankmew.benchmark.archetype.deploy.task.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Data
@Configuration
@ConfigurationProperties("threadpool")
public class TaskThreadPoolProperties {
    private Map<String, Task> task = new HashMap<>();

    public static final String TASK1 = "task1";

    @Getter
    public static class Task {
        private Integer poolSize = Runtime.getRuntime().availableProcessors() * 2 + 1;
        private Boolean allowWaitTermination = true;
        private Integer allowWaitTerminationTimeoutSecond = 60;

        public void setPoolSize(Integer poolSize) {
            if (this.poolSize != null && poolSize > 0) {
                this.poolSize = poolSize;
            }
        }

        public void setAllowWaitTermination(Boolean allowWaitTermination) {
            if (allowWaitTermination != null) {
                this.allowWaitTermination = allowWaitTermination;
            }
        }

        public void setAllowWaitTerminationTimeoutSecond(Integer allowWaitTerminationTimeoutSecond) {
            if (allowWaitTerminationTimeoutSecond != null
                    && allowWaitTerminationTimeoutSecond >= 0) {
                this.allowWaitTerminationTimeoutSecond = allowWaitTerminationTimeoutSecond;
            }
        }
    }
}
