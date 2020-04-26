package com.ltq.undertow.jol;

import java.util.concurrent.TimeUnit;

import org.openjdk.jol.info.ClassLayout;

//查看指令
//https://blog.csdn.net/hudashi/article/details/7062675
//https://blog.csdn.net/hudashi/article/details/7062781
public class Test {
    int a = 0;

    public static void main(String[] args) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);// 偏向锁启动时间是4s
        // 01 00 00 00 (00000001 00000000 00000000 00000000) (1) 无锁001 第一个0是无偏向锁标记
        // 05 00 00 00 (00000101 00000000 00000000 00000000) (5) 匿名偏向 101,首位是开启偏向标记

        Test t = new Test();
        String s = ClassLayout.parseInstance(t).toPrintable();
        System.out.println(s);
        synchronized (t) {
            // 05 48 09 03 (00000101 01001000 00001001 00000011) (50939909) 偏向线程101
            // d8 f2 66 02 (11011000 11110010 01100110 00000010) (40301272) 轻量级锁000

            s = ClassLayout.parseInstance(t).toPrintable();
            System.out.println(s);
        }
        // 轻量级锁、重量级锁：栈上Lock Record -->可重入记录
        Test[] list = new Test[10];
        s = ClassLayout.parseInstance(list).toPrintable();
        System.out.println(s);
    }
}