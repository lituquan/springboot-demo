package com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * Author: lituquan
 * Date: 2023/6/26
 */
@Slf4j
public abstract class DataBatch<T, K> {

    public int getBatchSize() {
        return 1000;
    }

    public void syncDataFromDc(Executor executor, List<T> intData) {
        int batchSize = getBatchSize(); // 每批次执行的任务数量
        Assert.isTrue(batchSize > 0, "batchSize 要大于0");
        Assert.notNull(executor);
        // 分批
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        for (int i = 0; i < intData.size(); i += batchSize) {
            int endIndex = Math.min(i + batchSize, intData.size());
            final List<T> batchEntries = intData.subList(i, endIndex);
            final int id = i;
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                // 计算
                List<K> results = handle(batchEntries);
                // 存储
                save(results);
            }, executor).exceptionally((e) -> {
                log.info("batch id:{}", id);
                e.printStackTrace();
                return null;
            });
            futures.add(future);
        }
        // 并发
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
    }

    // 存储
    public abstract void save(List<K> results);

    // 计算
    public abstract List<K> handle(List<T> intData);
}
