package com.ltq.undertow.bloomFilter;

public class BitMap {
    public int[] word;

    public BitMap(int size) {
        word = new int[size];
    }

    public int size() {
        return word.length * 32;
    }

    public void add(int n) throws Exception {
        if (n >= size()) {
            throw new Exception("超出范围");
        }
        int w = n >> 5;
        int offset = n & (31);// 31 是低5位
        word[w] |= (1 << offset);
    }

    public boolean get(int n) throws Exception {
        if (n >= size()) {
            throw new Exception("超出范围");
        }
        int w = n >> 5;
        if (w < 0) {
            System.out.println("1111：" + n);
        }
        int offset = n & (31);
        int bit = (word[w] & (1 << offset));
        return bit != 0;
    }

    public static void main(String[] args) throws Exception {
        int size = 10000;
        BitMap b = new BitMap(size);
        for (int i = 0; i < 100000; i++) {
            b.add(i);
        }
        for (int i = 0; i < 100000; i++) {
            if (!b.get(i)) {
                System.out.println(i);
            }
        }
    }
}