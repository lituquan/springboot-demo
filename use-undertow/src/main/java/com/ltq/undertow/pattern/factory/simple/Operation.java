package com.ltq.undertow.pattern.factory.simple;

public abstract class Operation {
    private double numberA;
    private double numberB;

    /**
     * @return double return the numberA
     */
    public double getNumberA() {
        return numberA;
    }

    /**
     * @param numberA the numberA to set
     */
    public void setNumberA(double numberA) {
        this.numberA = numberA;
    }

    /**
     * @return double return the numberB
     */
    public double getNumberB() {
        return numberB;
    }

    /**
     * @param numberB the numberB to set
     */
    public void setNumberB(double numberB) {
        this.numberB = numberB;
    }

    public abstract double getresult();
}