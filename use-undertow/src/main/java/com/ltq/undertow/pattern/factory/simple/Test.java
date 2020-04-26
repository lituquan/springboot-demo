package com.ltq.undertow.pattern.factory.simple;

public class Test {
    public static void main(String[] args) {
        String operator = "/";//
        Operation oper = OperationFactory.getOperation(operator);
        oper.setNumberA(10);
        oper.setNumberB(20);
        System.out.println(oper.getresult());
    }
}