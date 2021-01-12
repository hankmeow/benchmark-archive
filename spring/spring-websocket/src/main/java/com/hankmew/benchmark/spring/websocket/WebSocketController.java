package com.hankmew.benchmark.spring.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class WebSocketController {
    @Resource
    private WebSocketService webSocketService;

    /**
     * 初次订阅时，获取一些环境
     * @return
     */
    @SubscribeMapping(Constant.TOPIC_ENV)
    public Long env() {
        return webSocketService.getVersion();
    }

    /**
     * 接收客户端发来的信息，并通过指定主题返还给客户端
     * @param message
     * @return
     */
    @MessageMapping(Constant.APP_SPEECH)
    @SendTo(Constant.TOPIC_REPLY)
    public String reply(String message) {
        return message;
    }
}
