package com.example.delay.queue.component;

import com.example.delay.queue.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBlockingQueue;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserQueueTask {
    private final UserService userService;
    private final RBlockingQueue<String> rBlockingQueue;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    /**
     * 执行延时任务
     */
    @PostConstruct
    public void take() {
        executorService.submit(() -> {
                    while (true) {
                        try {
                            String job = rBlockingQueue.take();
                            log.info("job: {}",job);
                            userService.operate(job);
                        } catch (Exception e) {
                            e.printStackTrace();
                            log.info("job error:{}", e.getMessage());
                        }
                    }
                }
        );
    }
}