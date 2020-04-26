package com.ltq.undertow.blockqueue;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class ProducerConsumer {
    public static BlockingDeque<Integer> q = new LinkedBlockingDeque(1);

    public static void main(String[] args) throws InterruptedException {
        Producer p = new Producer(10, q);
        Consumer c = new Consumer(q);
        Thread pt = new Thread(p);
        Thread ct = new Thread(c);
        pt.start();
        ct.start();

        Thread.sleep(5000);
    }
}