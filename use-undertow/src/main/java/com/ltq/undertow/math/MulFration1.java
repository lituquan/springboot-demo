package com.ltq.undertow.math;

import java.util.Random;

public class MulFration1 implements Cal {

    public int a, b, c;

    public MulFration1() {
        Random rand = new Random();
        a = rand.nextInt(9) + 1;
        b = rand.nextInt(90) + 1 + a;
        c = rand.nextInt(98) + 2;
    }

    public MulFration1(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public String getQues() {
        Fraction fraction = new Fraction(b, c);// a*b/c
        return a + "*" + fraction.toString();
    }

    @Override
    public String getAnswer() {
        Fraction fraction = new Fraction(a * b, c);
        return fraction.toString();
    }

    public static void main(String[] args) {
        MulFration1 mul = new MulFration1();
        mul.a = 11;
        mul.b = 2;
        mul.c = 33;
        System.out.println(mul.getQues() + "=" + mul.getAnswer());
    }
}