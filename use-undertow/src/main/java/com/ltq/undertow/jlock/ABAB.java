package com.ltq.undertow.jlock;

public class ABAB {

    public static Object A = new Object();

    static class Word implements Runnable {
        public static String[] list = { "A", "B", "C", "D", "E" };

        @Override
        public void run() {
            synchronized (A) {
                for (int i = 0; i < list.length; i++) {
                    System.out.println(list[i]);
                    A.notifyAll();
                    try {
                        A.wait();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                A.notify();
            }
        }

    }

    static class Number implements Runnable {
        public static Integer[] list = { 1, 2, 3, 4, 5 };

        @Override
        public void run() {
            synchronized (A) {
                for (int i = 0; i < list.length; i++) {
                    System.out.println(list[i]);
                    A.notifyAll();
                    try {
                        A.wait();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }

        }

    }

    // https://blog.csdn.net/YYZZHC999/article/details/100543520
    public static void main(String[] args) {
        Thread t1 = new Thread(new Word());
        Thread t2 = new Thread(new Number());
        t1.start();
        t2.start();
    }
}