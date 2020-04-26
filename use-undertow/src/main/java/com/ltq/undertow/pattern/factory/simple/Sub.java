
package com.ltq.undertow.pattern.factory.simple;

public class Sub extends Operation {

    @Override
    public double getresult() {
        return getNumberA() - getNumberB();
    }

}