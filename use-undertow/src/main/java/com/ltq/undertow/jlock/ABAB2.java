package com.ltq.undertow.jlock;

import java.util.concurrent.LinkedTransferQueue;

public class ABAB2 {
    public static LinkedTransferQueue queue = new LinkedTransferQueue<String>();

    static class P implements Runnable {
        public static String[] list = { "A", "B", "C", "D", "E" };

        @Override
        public void run() {
            for (int i = 0; i < list.length; i++) {
                try {
                    // 先生产,在消费
                    queue.transfer(list[i].toString());
                    System.out.println(queue.take());
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }

    }

    static class C implements Runnable {
        public static String[] list = { "1", "2", "3", "4", "5" };

        @Override
        public void run() {
            for (int i = 0; i < list.length; i++) {
                try {
                    // 先消费,再生产
                    System.out.println(queue.take());
                    queue.transfer(list[i].toString());
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }

    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new P());
        Thread t2 = new Thread(new C());
        t1.start();
        t2.start();
    }
}