package com.ltq.undertow.volatile1;

/*
问题：
    执行引擎--线程工作副本--主内存

    线程1--use--flag++--assign--store\write写入主内存
    线程2--use--flag++--assign--store\write写入主内存


    由于在读取之后不会再读取，所以计算期间数据是使用工作副本。
    线程1的修改不会马上被线程2读取，而是下次使用再读取。再执行++期间，数据是旧的。
    flag++-->flag++

    读取flag【这里失效会触发load、store】
    flag+1
    flag赋值
*/
public class Test3 {
    public static volatile int flag = 0;
    public static int max = 1000;

    public static void incr() {
        flag++;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < max; i++) {
                        // incr();
                        flag++;
                    }
                }
            };
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
        // Thread.sleep(2000);
        System.out.println(flag);
    }
}