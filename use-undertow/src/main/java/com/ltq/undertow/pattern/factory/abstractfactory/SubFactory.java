package com.ltq.undertow.pattern.factory.abstractfactory;

import com.ltq.undertow.pattern.factory.simple.Operation;
import com.ltq.undertow.pattern.factory.simple.Sub;

public class SubFactory implements IFactory {

    @Override
    public Operation getOperation() {
        return new Sub();
    }

    @Override
    public Show getshow() {
        return new SubShow();
    }

}