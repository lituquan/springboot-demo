package com.ltq.undertow.pattern.template;

public abstract class Standard {

    public abstract void introduce();

    public abstract void first();

    public abstract void second();

    // 模板化执行过程
    public void stand() {
        introduce();
        first();
        second();
    }
}