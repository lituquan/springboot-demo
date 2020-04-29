package com.ltq.undertow.jlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ABAB1 {

    public static Lock A = new ReentrantLock();
    public static Condition Bp = A.newCondition();
    public static Condition Bc = A.newCondition();

    static class Word implements Runnable {
        public static String[] list = { "A", "B", "C", "D", "E" };

        @Override
        public void run() {
            A.lock();
            try {
                for (int i = 0; i < list.length; i++) {
                    System.out.println(list[i]);
                    Bc.signal();// 唤醒队列中第一个节点
                    Bp.await();// 加入队列
                }
                Bc.signal();// 避免最后一个等待
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                A.unlock();
            }

        }
    }

    static class Number implements Runnable {
        public static Integer[] list = { 1, 2, 3, 4, 5 };

        @Override
        public void run() {
            try {
                A.lock();
                for (int i = 0; i < list.length; i++) {
                    System.out.println(list[i]);
                    Bp.signal();
                    Bc.await();
                }
                Bp.signal();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                A.unlock();
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