package com.ltq.undertow.volatile1;

/*
问题：
    看起来flag的值是一致的。
    线程1--工作内存：变量副本--主内存
    线程2--工作内存：变量副本--主内存
   
    线程1修改数据，写入主内存
    线程2没有触发load--store，线程2不会读取主内存，所以线程1修改的变量没有被读取到。

    volatile 会在数据修改assign后，马上store-write到主内存，并让其他线程的数据试下。
    其他线程在下次读取use之前，需要进行load--read操作。
*/
public class Test2 {
    public static volatile boolean flag = false;

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