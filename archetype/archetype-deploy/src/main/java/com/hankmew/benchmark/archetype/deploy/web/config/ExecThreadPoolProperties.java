package com.hankmew.benchmark.archetype.deploy.web.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Data
@Configuration
@ConfigurationProperties("threadpool")
public class ExecThreadPoolProperties {
    private Map<String, Exec> execution = new HashMap<>();

    public static final String EXEC1 = "exec1";

    public static final String EXEC2 = "exec2";

    @Getter
    public static class Exec {
        private Integer coreSize = Runtime.getRuntime().availableProcessors() * 2 + 1;
        private Integer queueCapacity = 0;
        private Integer maxSize = coreSize;
        private Boolean allowCoreThreadTimeout = false;
        private Integer keepAliveSecond = 60;
        private Boolean allowWaitTermination = true;
        private Integer allowWaitTerminationTimeoutSecond = 60;

        public void setCoreSize(Integer coreSize) {
            if (this.coreSize != null && coreSize > 0) {
                this.coreSize = coreSize;
            }
        }

        public void setQueueCapacity(Integer queueCapacity) {
            if (queueCapacity != null && queueCapacity > 0) {
                this.queueCapacity = queueCapacity;
            }
        }

        public void setMaxSize(Integer maxSize) {
            if (maxSize != null && maxSize > coreSize) {
                this.maxSize = maxSize;
            }
        }

        public void setAllowCoreThreadTimeout(Boolean allowCoreThreadTimeout) {
            if (allowCoreThreadTimeout != null) {
                this.allowCoreThreadTimeout = allowCoreThreadTimeout;
            }
        }

        public void setKeepAliveSecond(Integer keepAliveSecond) {
            if (keepAliveSecond != null && keepAliveSecond > 0) {
                this.keepAliveSecond = keepAliveSecond;
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
