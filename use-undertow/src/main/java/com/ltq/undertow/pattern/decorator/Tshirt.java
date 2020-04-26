package com.ltq.undertow.pattern.decorator;

public class Tshirt extends Decorator {

    public Tshirt(Show show) {
        super(show);
    }

    @Override
    public void show() {
        super.show();// 维护旧的行为
        more();
    }

    private void more() {
        System.out.println("大T恤");
    }
}