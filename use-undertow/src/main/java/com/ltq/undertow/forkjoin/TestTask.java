package com.ltq.undertow.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class TestTask extends RecursiveTask<Integer> {
    public static int Max = 2;
    private int start;
    private int end;

    public TestTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        boolean can = (end - start) <= Max;
        int sum = 0;
        if (can) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            // 任务分割
            int middle = (start + end) / 2;
            TestTask l = new TestTask(start, middle);
            TestTask r = new TestTask(middle + 1, end);
            // 提交任务-- >到底有多少个线程
            l.fork();
            r.fork();

            // 获取结果
            sum += l.join();
            sum += r.join();
        }
        return sum;
    }

    public static void main(String[] args) {
        long startT = System.currentTimeMillis();
        ForkJoinPool p = new ForkJoinPool();
        TestTask task = new TestTask(1, 100);
        Future<Integer> result = p.submit(task);
        try {
            int sum = result.get();
            long endt = System.currentTimeMillis();
            System.out.println(sum + "--" + (endt - startT));
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}