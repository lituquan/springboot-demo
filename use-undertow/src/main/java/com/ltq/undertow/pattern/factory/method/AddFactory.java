package com.ltq.undertow.pattern.factory.method;

import com.ltq.undertow.pattern.factory.simple.Add;
import com.ltq.undertow.pattern.factory.simple.Operation;

public class AddFactory implements IFactory {

    @Override
    public Operation getOperation() {
        return new Add();
    }

}