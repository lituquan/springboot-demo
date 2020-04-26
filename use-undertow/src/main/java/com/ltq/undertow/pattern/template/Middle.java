package com.ltq.undertow.pattern.template;

public class Middle extends Standard {

    @Override
    public void first() {
        System.out.println("6点起来");
    }

    @Override
    public void second() {
        System.out.println("骑自行车");
    }

    @Override
    public void introduce() {
        System.out.println("我的身份是：中学生");
    }

}