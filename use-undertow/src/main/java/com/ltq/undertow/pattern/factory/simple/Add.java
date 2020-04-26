
package com.ltq.undertow.pattern.factory.simple;

public class Add extends Operation {

    @Override
    public double getresult() {
        return getNumberA() + getNumberB();
    }

}