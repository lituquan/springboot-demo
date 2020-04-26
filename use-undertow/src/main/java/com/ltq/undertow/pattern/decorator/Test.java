package com.ltq.undertow.pattern.decorator;

public class Test {
    public static void main(String[] args) {
        Person pc = new Person();

        Tshirt ts = new Tshirt(pc);// 维护旧对象
        Jeans js = new Jeans(ts);
        js.show();// 执行所有行为
    }
}