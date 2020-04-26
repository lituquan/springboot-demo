package com.ltq.undertow.pattern.template;

public class Primary extends Standard {
    @Override
    public void first() {
        System.out.println("7点起来");
    }

    @Override
    public void second() {
        System.out.println("坐校车");
    }

    @Override
    public void introduce() {
        System.out.println("我的身份是：小学生");
    }

}