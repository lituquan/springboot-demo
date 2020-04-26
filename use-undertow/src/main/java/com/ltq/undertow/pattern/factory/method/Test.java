package com.ltq.undertow.pattern.factory.method;

import com.ltq.undertow.pattern.factory.simple.Operation;

public class Test {
    public static void main(String[] args) {
        IFactory factory = new PowFactory();
        Operation oper = factory.getOperation();
        oper.setNumberA(10);
        oper.setNumberB(5);
        System.out.println(oper.getresult());
    }
}