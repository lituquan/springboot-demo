package com.ltq.undertow.math;

import java.text.DecimalFormat;
import java.util.Random;

public class MulFration5 implements Cal {
    public Double a, b;
    DecimalFormat df = new DecimalFormat("####.##");

    public MulFration5(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public MulFration5() {
        Random rand = new Random();
        a = rand.nextDouble() * 10;
        b = rand.nextDouble() * 10;
        if (rand.nextBoolean()) {
            a = -a;
        } else {
            b = -b;
        }
    }

    @Override
    public String getQues() {
        a = Double.valueOf(df.format(a));
        b = Double.valueOf(df.format(b));
        return a + "〇" + b;
    }

    @Override
    public String getAnswer() {
        double helper = 0.0001;
        if (a - b == 0) {
            return "=";
        }
        if (a * b > helper) {
            return (a - b > helper) ? ">" : "<";
        } else if (a >= 0 && b < 0) {
            return ">";
        } else {
            return "<";
        }
    }

    public static void main(String[] args) {
        MulFration5 mul = new MulFration5(1.00, -20.000);
        // mul = new MulFration5();
        System.out.println(mul.getQues() + ":" + mul.getAnswer());
    }
}