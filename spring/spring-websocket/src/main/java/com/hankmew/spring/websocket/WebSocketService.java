package com.hankmew.spring.websocket;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class WebSocketService {
    @Resource
    private SimpMessagingTemplate simpMessagingTemplate;
    private Long version = 1L;
    private Long versionOld = version;

    public Long getVersion() {
        version = version + 1;
        return version;
    }

    /**
     * 定时向客户端推送环境更新
     */
    @Scheduled(fixedRate = 1000)
    public void env() {
        if (versionOld.equals(version)) {
            return;
        }
        versionOld = version;
        simpMessagingTemplate.convertAndSend(Constant.TOPIC_ENV, version);
    }

    /**
     * 定时向客户端广播当前时间
     */
    @Scheduled(fixedRate = 10000)
    public void cronPushMessageToClient() {
        simpMessagingTemplate.convertAndSend(Constant.TOPIC_BROADCAST, System.currentTimeMillis());
    }
}
