package com.ltq.undertow.jlock;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestLock implements Runnable {
    // 共享变量
    public static int flag = 0;
    public static Lock lock = new ReentrantLock();
    // lock=new ReentrantReadWriteLock();
    ArrayBlockingQueue fairQueue = new ArrayBlockingQueue(1000, true);
    public static int max = 1000;

    public static void incr() {
        lock.lock();
        LockSupport.park();
        ;
        // lock=new ReentrantReadWriteLock();
        try {
            flag++;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            Thread t = new Thread(new TestLock());
            threads[i] = t;
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
        System.out.println(flag);
    }

    @Override
    public void run() {
        for (int i = 0; i < max; i++) {
            incr();
        }
    }
}