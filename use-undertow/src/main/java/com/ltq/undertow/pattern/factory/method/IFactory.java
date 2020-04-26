package com.ltq.undertow.pattern.factory.method;

import com.ltq.undertow.pattern.factory.abstractfactory.Show;
import com.ltq.undertow.pattern.factory.simple.Operation;

public interface IFactory {
     public Operation getOperation();
}