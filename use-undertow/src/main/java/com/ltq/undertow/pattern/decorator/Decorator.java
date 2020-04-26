package com.ltq.undertow.pattern.decorator;

public class Decorator implements Show {
    Show shower = null;

    public Decorator(Show show) {// 维护已有的对象
        this.shower = show;
    }

    @Override
    public void show() {
        shower.show(); // 维护旧的行为
    }
}