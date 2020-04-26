package com.ltq.undertow.pattern.strategy;

import com.ltq.undertow.pattern.factory.simple.Operation;

public class Context {
    // 维护一个策略对象
    Operation oper = null;

    public Context(Operation operation) {
        this.oper = operation;
    }

    public double show() {
        double result = oper.getresult();
        System.out.println(result);
        return result;
    }
}