package com.ltq.undertow.pattern.decorator;

public class Jeans extends Decorator {

    public Jeans(Show show) {
        super(show);
    }

    @Override
    public void show() {
        super.show();// 维护旧的行为
        more();
    }

    private void more() {
        System.out.println("牛仔裤");
    }
}