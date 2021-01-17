package com.hankmew.benchmark.spring.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RabbitListener(queues = Constant.QUEUE)
public class Consumer {
    @RabbitHandler
    public void recieved(String msg) {
        log.info("{} recieved message: {}", Constant.QUEUE, msg);
    }
}
