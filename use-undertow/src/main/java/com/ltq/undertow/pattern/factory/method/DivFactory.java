package com.ltq.undertow.pattern.factory.method;

import com.ltq.undertow.pattern.factory.simple.Div;
import com.ltq.undertow.pattern.factory.simple.Operation;

public class DivFactory implements IFactory {

    @Override
    public Operation getOperation() {
        return new Div();
    }

}