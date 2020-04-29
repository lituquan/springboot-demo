package com.ltq.undertow.pattern.adapter;

public class Mp4Impl implements Mp4 {
    @Override
    public void play() {
        System.out.println("Mp4 播放中...");
    }

    @Override
    public void introduce() {
        System.out.println("我是Mp4 播放器。");
    }
}