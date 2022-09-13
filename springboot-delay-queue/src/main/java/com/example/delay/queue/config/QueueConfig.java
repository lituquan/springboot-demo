package com.example.delay.queue.config;

import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {
    private final String queueName = "test:queue";

    @Bean
    public RBlockingQueue<String> rBlockingQueue(RedissonClient redissonClient) {
        return redissonClient.getBlockingQueue(queueName);
    }

    @Bean
    public RDelayedQueue<String> rDelayedQueue(RedissonClient redissonClient, @Qualifier("rBlockingQueue") RBlockingQueue<String> blockQueue) {
        return redissonClient.getDelayedQueue(blockQueue);
    }
}