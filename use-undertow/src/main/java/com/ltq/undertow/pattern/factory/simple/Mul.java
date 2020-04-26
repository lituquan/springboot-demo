
package com.ltq.undertow.pattern.factory.simple;

public class Mul extends Operation {

    @Override
    public double getresult() {
        return getNumberA() * getNumberB();
    }

}