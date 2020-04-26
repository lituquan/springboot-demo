package com.ltq.undertow.blockqueue;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    public int max;

    public BlockingQueue q = null;

    public Producer(int max, BlockingQueue q) {
        this.max = max;
        this.q = q;
    }

    @Override
    public void run() {
        for (int i = 0; i < max; i++) {
            try {
                q.put(i);
                System.out.println("producer :" + i);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}