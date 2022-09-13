package com.example.delay.queue;

import org.junit.jupiter.api.Test;
import org.redisson.api.RDelayedQueue;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class ApplicationTests {

    @Resource
    RDelayedQueue<String> rDelayedQueue;

    @Test
    void contextLoads() {
    }

    /**
     * 生产延时任务
     */
    @Test
    void submit() throws InterruptedException {
        long delaySeconds = 1;
        rDelayedQueue.offer("action submit 1 in:"+new Date(), delaySeconds, TimeUnit.SECONDS);

        delaySeconds = 2;
        rDelayedQueue.offer("action submit 2 in:"+new Date(), delaySeconds, TimeUnit.SECONDS);

        Thread.sleep(10000);
    }
}
