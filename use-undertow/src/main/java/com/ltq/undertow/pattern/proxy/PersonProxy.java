package com.ltq.undertow.pattern.proxy;

import com.ltq.undertow.pattern.decorator.Show;

public class PersonProxy implements Show {
    public Show target = null;

    public PersonProxy(Show target) {
        this.target = target;
    }

    @Override
    public void show() {
        System.out.println("开始代理");
        target.show();
        System.out.println("结束代理");
    }

}