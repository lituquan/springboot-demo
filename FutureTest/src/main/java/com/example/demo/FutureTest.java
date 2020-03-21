package com.example.demo;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class FutureTest {
    public static void futureAndCallable() {
        // callable
        final Callable<Long> call = new MyCall();
        // 获取处理器核数
        final int poolSize = Runtime.getRuntime().availableProcessors();
        System.out.println("futureAndCallable:" + poolSize);

        // 线程池
        final ExecutorService exec = Executors.newFixedThreadPool(poolSize);
        final Future<Long> submit = exec.submit(call);

        try {
            final Long long1 = submit.get();
            Thread.sleep(600);
            System.out.println(System.currentTimeMillis() - long1);
            exec.shutdown();
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void futureAndComplete() {
        // callable
        final Callable<Long> call = new MyCall();
        // 获取处理器核数
        final int poolSize = Runtime.getRuntime().availableProcessors();
        System.out.println("futureAndCallable:" + poolSize);

        // 线程池
        final ExecutorService exec = Executors.newFixedThreadPool(poolSize);
        CompletionService<Long> completionService = new ExecutorCompletionService<>(exec);

        try {
            Long long1 = completionService.take().get();
            Thread.sleep(600);
            System.out.println(System.currentTimeMillis() - long1);
            exec.shutdown();
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void futureTaskAndThread() {

        final MyTask task = new MyTask(new MyCall());
        final Thread t = new Thread(task);
        t.start();

        try {
            Thread.sleep(1000);
            task.get();
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    public static void main(final String[] args) {
        futureAndCallable();
        futureAndComplete();
        futureTaskAndThread();

    }
}
class MyCall implements Callable<Long> {
    @Override
    public Long call() throws Exception {
        System.out.println(Thread.currentThread().getName());
        return System.currentTimeMillis();
    }
}

class MyTask extends FutureTask<Long> {

    public MyTask(final Runnable runnable, final Long result) {
        super(runnable, result);
        // TODO Auto-generated constructor stub
    }
    public MyTask(final Callable runnable) {
        super(runnable);
        // TODO Auto-generated constructor stub
    }
  
}