package com.ltq.undertow.lock;

public class LockTest {
    public static int max = 1000;
    public static int flag = 0;

    public static void incr() {
        flag++;
    }

    public static void main(String[] args) throws InterruptedException {
        // final RedisLock lock = new RedisLock("localhost", 6379);

        final ZookeeperLock lock = new ZookeeperLock("localhost:2181");
        Thread[] threads = new Thread[10];
        final String key = "1";
        for (int i = 0; i < threads.length; i++) {
            final String value = i + "";
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    // while 相当于空循环自旋
                    lock.lock(key, value);
                    for (int i = 0; i < max; i++) {
                        incr();
                    }
                    lock.unlock(key, value);
                }
            });
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
        System.out.println("============= flag =" + flag);
    }
}