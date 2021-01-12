package com.hankmew.benchmark.library.redisson;

import org.junit.jupiter.api.Test;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;

public class RedissonTest {
    @Test
    public void testRedisson() {
        Config config = new Config();
        config.setTransportMode(TransportMode.EPOLL);
        config.useSingleServer().setAddress("192.168.192.168:6379");
        config.useSingleServer().setDatabase(1);
        RedissonClient redissonClient = Redisson.create(config);
    }
}
