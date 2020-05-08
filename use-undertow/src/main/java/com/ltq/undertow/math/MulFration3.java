package com.ltq.undertow.math;

import java.util.Random;

public class MulFration3 implements Cal {
    public Fraction fraction = null;
    public Fraction fraction2 = null;

    public MulFration3() {
        Random rand = new Random();
        long a = rand.nextInt(9) + 1;
        long b = rand.nextInt(90) + 1 + a;

        long c = rand.nextInt(9) + 1;
        long d = rand.nextInt(90) + 1 + c;
        fraction = new Fraction(a, b);// a/b*c/d
        fraction2 = new Fraction(c, d);// a/b*c/d
    }

    public MulFration3(long a, long b, long c, long d) {
        fraction = new Fraction(a, b);// a/b*c/d
        fraction2 = new Fraction(c, d);// a/b*c/d
    }

    @Override
    public String getQues() {
        return fraction.toString() + "*" + fraction2.toString();
    }

    @Override
    public String getAnswer() {
        Fraction temp = new Fraction(fraction.getNumerator() * fraction2.getNumerator(),
                fraction.getDenominator() * fraction2.getDenominator());
        return temp.toString();
    }

    public static void main(String[] args) {
        MulFration3 mul = new MulFration3(2, 33, 11, 20);
        System.out.println(mul.getQues() + "=" + mul.getAnswer());
    }
}