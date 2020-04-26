package com.ltq.undertow.pattern.factory.abstractfactory;

import com.ltq.undertow.pattern.factory.abstractfactory.Show;
import com.ltq.undertow.pattern.factory.simple.Operation;

public interface IFactory {
     public Operation getOperation();

     // 抽象工厂支持获取多种产品
     public Show getshow();
}