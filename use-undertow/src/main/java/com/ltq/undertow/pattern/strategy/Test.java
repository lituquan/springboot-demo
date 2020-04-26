package com.ltq.undertow.pattern.strategy;

import com.ltq.undertow.pattern.factory.simple.Operation;
import com.ltq.undertow.pattern.factory.simple.OperationFactory;

public class Test {

    public static void main(String[] args) {
        // 工厂控制策略对象的创建
        String op = "";// 加减乘除都是一个策略
        Operation oper = OperationFactory.getOperation(op);
        oper.setNumberA(100);
        oper.setNumberB(50);

        // 上下文对象控制策略的调用
        Context context = new Context(oper);
        context.show();
    }
}