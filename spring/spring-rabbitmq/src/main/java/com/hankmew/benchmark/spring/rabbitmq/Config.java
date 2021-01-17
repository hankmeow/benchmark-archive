package com.hankmew.benchmark.spring.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public Queue benchmark() {
        return new Queue(Constant.QUEUE);
    }
}
