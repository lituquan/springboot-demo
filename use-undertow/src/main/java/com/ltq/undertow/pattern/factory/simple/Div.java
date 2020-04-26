
package com.ltq.undertow.pattern.factory.simple;

public class Div extends Operation {

    @Override
    public double getresult() {
        if (getNumberB() == 0) {
            System.out.println("不能除以0");
            return 0;
        }
        return getNumberA() / getNumberB();
    }

}