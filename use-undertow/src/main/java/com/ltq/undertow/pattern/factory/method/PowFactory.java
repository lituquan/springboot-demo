package com.ltq.undertow.pattern.factory.method;

import com.ltq.undertow.pattern.factory.simple.Operation;
import com.ltq.undertow.pattern.factory.simple.Pow;

public class PowFactory implements IFactory {

    @Override
    public Operation getOperation() {
        return new Pow();
    }

}