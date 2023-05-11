package com.example.cardealer.entities.sale;

public enum DISCOUNT {
    A(0.1),
    B(0.95),
    C(0.90),
    D(0.85),
    E(0.80),
    F(0.70),
    G(0.60),
    M(0.50);

    private double numVal;

    DISCOUNT(double numVal){
        this.numVal = numVal;
    }

    public double getNumVal() {
        return numVal;
    }
}
