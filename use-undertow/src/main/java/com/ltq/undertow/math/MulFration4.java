package com.ltq.undertow.math;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class MulFration4 implements Cal {
    public List<Fraction> fraction = new ArrayList<>();

    public MulFration4() {

    }

    public MulFration4(int len) {
        Random rand = new Random();
        for (int i = 0; i < len; i++) {
            long c = rand.nextInt(9) + 1;
            long d = rand.nextInt(40) + 1 + c;
            fraction.add(new Fraction(c, d));
        }

    }

    @Override
    public String getQues() {
        if (fraction.size() < 2) {
            return "";
        }
        String[] strs = new String[fraction.size()];
        for (int i = 0; i < fraction.size(); i++) {
            strs[i] = fraction.get(i).toString();
        }
        return String.join("*", strs);
    }

    @Override
    public String getAnswer() {
        long number = 1;
        long denominator = 1;
        for (int i = 0; i < fraction.size(); i++) {
            number *= fraction.get(i).getNumerator();
            denominator *= fraction.get(i).getDenominator();
        }
        Fraction temp = new Fraction(number, denominator);
        return temp.toString();
    }

    public static void main(String[] args) {
        MulFration4 mul = new MulFration4(2);
        // 2, 33, 11, 20
        // mul.fraction.add(new Fraction(2,33));
        // mul.fraction.add(new Fraction(11,20));
        System.out.println(mul.getQues() + "=" + mul.getAnswer());
    }
}