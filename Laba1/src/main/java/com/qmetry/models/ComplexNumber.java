package com.qmetry.models;

public class ComplexNumber {

    private double real;
    private double imag;

    public static final int ZERO = 0;

    public ComplexNumber(double real)
    {
        this(real, ZERO);
    }

    public ComplexNumber(double real, double imag)
    {
        this.real = real;
        this.imag = imag;
    }

    public double real()
    {
        return this.real;
    }

    public double imag()
    {
        return this.imag;
    }
}
