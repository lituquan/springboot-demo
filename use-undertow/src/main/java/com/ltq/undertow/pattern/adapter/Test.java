package com.ltq.undertow.pattern.adapter;

public class Test {
    public static void main(String[] args) {
        Mp4 mp4 = new Mp4Impl();
        Player pl = new PlayerMp4(mp4);
        pl.action();
    }
}