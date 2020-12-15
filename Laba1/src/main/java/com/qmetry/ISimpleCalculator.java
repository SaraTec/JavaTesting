package com.qmetry;

public interface ISimpleCalculator {
	double sum(double a, double b);

	double subtraction(double a, double b);

	double multiplication(double a, double b);

	double divison(double a, double b) throws Exception;

	boolean equalIntegers(double a, double b);
}
