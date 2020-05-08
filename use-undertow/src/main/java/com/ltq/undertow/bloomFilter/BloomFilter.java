package com.ltq.undertow.bloomFilter;

import java.util.LinkedList;
import java.util.List;

public class BloomFilter {
    public BitMap data;
    public int k;
    public int size;

    public BloomFilter(int length, int k) {
        data = new BitMap(length);
        this.k = k;
        size = this.data.word.length * 32;
        System.out.println(size);
    }

    public void add(String key) throws Exception {
        for (int i = 0; i < k; i++) {
            data.add(hash(key, i + 1));
        }
    }

    // https://www.jianshu.com/p/824244877ca5
    private int hash(String key, int Q) {
        int h = 0;
        int off = 0;
        char val[] = key.toCharArray();
        int len = key.length();
        for (int i = 0; i < len; i++) {
            h = (31 + Q) * h + val[off++];
        }
        return h % (size);
    }

    public boolean Exist(String key) throws Exception {
        for (int i = 0; i < k; i++) {
            if (!data.get(hash(key, i + 1))) { // 有一个是0就不存在
                return false;
            }
        }
        return true; // 可能存在
    }

    public static void main(String[] args) throws Exception {
        BloomFilter bloom = new BloomFilter(1000, 3);
        int a = 8000;
        for (int i = 0; i < a; i++) {
            bloom.add(i + "");
            // System.out.println(a);
        }
        System.out.println("=====================");
        int count = 0;
        for (int i = 0; i < 2 * a; i++) {
            if (bloom.Exist(i + "")) {
                if (i > a) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}