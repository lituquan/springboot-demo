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
    public static void extendThread() {

        Thread t = new Thread() {
            @Override
            public void run() {
                System.out.println("extendThread thread");
            }
        };
        t.start();// 会调用本地方法生成线程
        // t.run();//普通方法调用,不会生成线程
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void implRunnable() {

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("implRunnable thread");
            }
        });
        t.start();// 会调用本地方法生成线程
        // t.run();//普通方法调用,不会生成线程
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
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
    
        // 线程池
        int poolSize=1;
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
    public static void futureTaskAndThread(){
        MyTask task = new MyTask(new MyCall());
        Thread t = new Thread(task);
        t.start();

        try {
            Thread.sleep(1000);
            task.get();
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    public static void futureTaskRunnableAndThread() {
        final Result r = new Result();
        FutureTask<Result> fib30 = new FutureTask<>(
            new Runnable() {
            @Override
            public void run() {
                //r 是外部final對象,且有字段作為,r相當於容器
                r.result = System.currentTimeMillis();
            }
        
        }
        , r);
        try {
            Thread.sleep(5000);
            System.out.printf("第30個費式數:%d%n", fib30.get().result);
        } catch(Exception ex) {
            ex.printStackTrace();
        }        
    }
    public static void main(final String[] args) {
        implRunnable();
        extendThread();
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

    public MyTask( Runnable runnable,  Long result) {
        super(runnable, result);
        // TODO Auto-generated constructor stub
    }
    public MyTask( Callable runnable) {
        super(runnable);
        // TODO Auto-generated constructor stub
    }
  
}


class Result {
    public long result;
}
