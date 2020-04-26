package com.ltq.undertow.algorithm;

import java.util.Random;

public class Heap {
    private int max = 1000;
    public int[] mainArray = new int[max];
    public int len = 0;

    // 构造
    public void createHeap(int[] arr) {
        // 插入队尾
        // 上浮
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
            insert(arr[i]);
        }
        System.out.println("============ end");
    }

    public void show() {
        for (int j = 0; j < len; j++) {
            System.out.println(mainArray[j]);
        }
        System.out.println("============ end");
    }

    // 插入
    public void insert(int key) {
        if (len == max - 1) {
            return;
        }
        mainArray[len] = key;
        len++;
        up();
    }

    public void up() {
        // 和父节点比较
        if (len <= 1) {
            return;
        }
        int child = len - 1;
        int parent = (child - 1) / 2;
        int temp = mainArray[child];
        boolean flag = false;
        while (child > 0) { // 冒泡过程
            if (temp < mainArray[parent]) {
                mainArray[child] = mainArray[parent];
                child = parent;
                parent = (child - 1) / 2;
                flag = true;
            } else {
                break;
            }
        }
        mainArray[child] = temp;
    }

    // 删除最小元素
    public int del() {
        int result = mainArray[0];
        if (len == 1) {
            mainArray[0] = 0;
            return result;
        }
        mainArray[0] = mainArray[len - 1];
        int parent = 0;
        int lchild = 2 * parent + 1;
        int temp = mainArray[0];
        int rchild = lchild + 1;
        while (lchild < len && rchild < len) {
            int min = rchild;
            if (mainArray[lchild] < mainArray[rchild]) {
                min = lchild;
            }
            if (mainArray[min] < temp) {
                mainArray[parent] = mainArray[min];
                parent = min;
                lchild = 2 * parent + 1;
                rchild = lchild + 1;
            } else {
                break;
            }
        }
        mainArray[parent] = temp;

        mainArray[len - 1] = 0;
        len--;
        return result;
    }

    public static void main(String[] args) {
        Heap heap = new Heap();
        int[] arr = new int[100];
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(10000) + 1;
        }
        heap.createHeap(arr);
        heap.show();
        for (int i = 0; i < arr.length; i++) {
            System.out.println(heap.del());
        }
        System.out.println("==");
    }
}