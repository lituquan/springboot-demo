package com.ltq.undertow.pattern.factory.abstractfactory;

import com.ltq.undertow.pattern.factory.simple.Operation;

public class Test {
    public static void main(String[] args) {
        IFactory factory = new AddFactory();
        Operation oper = factory.getOperation();
        oper.setNumberA(10.0);
        oper.setNumberB(5.0);
        System.out.println(oper.getresult());

        Show show = factory.getshow();
        show.setNumberA(10.0);
        show.setNumberB(5.0);
        show.show();
    }
}