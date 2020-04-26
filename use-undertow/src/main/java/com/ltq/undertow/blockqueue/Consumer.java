package com.ltq.undertow.blockqueue;

import java.util.concurrent.BlockingDeque;

public class Consumer implements Runnable {
    public BlockingDeque<Integer> q;

    public Consumer(BlockingDeque<Integer> q) {
        this.q = q;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int result = q.take();
                System.out.println("Consumer :" + result);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}