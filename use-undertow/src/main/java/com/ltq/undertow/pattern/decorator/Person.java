package com.ltq.undertow.pattern.decorator;

public class Person implements Show {

    @Override
    public void show() {
        System.out.println("我今天穿的衣服是：");
    }

}