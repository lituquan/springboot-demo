package com.ltq.undertow.pattern.factory.method;

import com.ltq.undertow.pattern.factory.simple.Operation;
import com.ltq.undertow.pattern.factory.simple.Sub;

public class SubFactory implements IFactory {

    @Override
    public Operation getOperation() {
        return new Sub();
    }

}