package com.ltq.undertow.pattern.proxy;

import com.ltq.undertow.pattern.decorator.Person;
import com.ltq.undertow.pattern.decorator.Show;

public class Test {
    public static void main(String[] args) {
        Show target = new Person();
        Show pProxy = new PersonProxy(target);
        pProxy.show();
    }
}