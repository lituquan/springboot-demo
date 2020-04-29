package com.ltq.undertow.pattern.adapter;

public class PlayerMp4 implements Player {
    public Mp4 mp4;

    public PlayerMp4(Mp4 mp4) {
        this.mp4 = mp4;
    }

    /*
     * 1.接口转换 实现Player接口的action 方法,调用了Mp4的play方法
     * 
     * 2.部分实现 Mp4的introduce方法没有被使用
     */
    @Override
    public void action() {
        mp4.play();
    }
}