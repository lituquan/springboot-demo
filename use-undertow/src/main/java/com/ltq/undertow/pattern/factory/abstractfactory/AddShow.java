package com.ltq.undertow.pattern.factory.abstractfactory;

public class AddShow extends Show {

    @Override
    public void show() {
        System.out.println(getNumberA() + "+" + getNumberB());
    }

}