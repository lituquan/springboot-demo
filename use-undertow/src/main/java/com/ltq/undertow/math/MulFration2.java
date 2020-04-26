package com.ltq.undertow.math;

import java.util.Random;

public class MulFration2 implements Cal {
    public int a, b, c;

    public MulFration2() {
        Random rand = new Random();
        a = rand.nextInt(9) + 1;
        b = rand.nextInt(90) + 1 + a;
        c = rand.nextInt(98) + 2;
    }

    @Override
    public String getQues() {
        Fraction fraction = new Fraction(a, b);// a/b*c
        return fraction.toString() + "*" + a;
    }

    @Override
    public String getAnswer() {
        Fraction fraction = new Fraction(a * c, b);
        return fraction.toString();
    }

    public static void main(String[] args) {
        MulFration2 mul = new MulFration2();
        mul.a = 2;
        mul.b = 33;
        mul.c = 11;
        System.out.println(mul.getQues() + "=" + mul.getAnswer());
    }
}