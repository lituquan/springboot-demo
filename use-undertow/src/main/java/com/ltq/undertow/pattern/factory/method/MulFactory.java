package com.ltq.undertow.pattern.factory.method;

import com.ltq.undertow.pattern.factory.simple.Mul;
import com.ltq.undertow.pattern.factory.simple.Operation;

public class MulFactory implements IFactory {

    @Override
    public Operation getOperation() {
        return new Mul();
    }

}