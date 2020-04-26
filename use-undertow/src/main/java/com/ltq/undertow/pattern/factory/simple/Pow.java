
package com.ltq.undertow.pattern.factory.simple;

public class Pow extends Operation {

    @Override
    public double getresult() {
        return Math.pow(getNumberA(), getNumberB());
    }

}