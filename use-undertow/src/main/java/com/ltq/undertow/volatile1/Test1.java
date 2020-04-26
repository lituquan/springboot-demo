package com.ltq.undertow.volatile1;

/*
问题：
    看起来flag的值是一致的。
    线程--工作内存：变量副本--主内存
    只有一个输出。

    线程如果没有触发副本回写，那么线程内修改的变量不会被其他线程读取到。
*/
public class Test1 {
    public static boolean flag = false;

    public static void main(String[] args) {
        Thread t1 = new Thread() {
            public void run() {
                while (!flag) {

                }
                flag = false;
                System.out.println("flag is true");
            }
        };
        t1.start();
        flag = true;
        System.out.println("");
        while (flag) {

        }
        System.out.println("flag is false");
    }
}