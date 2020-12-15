package com.qmetry;

import com.qmetry.models.ComplexNumber;
import org.apache.log4j.Logger;

public class ComplexCalculator {
    final static Logger logger = Logger.getLogger(ComplexCalculator.class);

    public ComplexNumber add(ComplexNumber num1, ComplexNumber num2)
    {
        logger.info("add method");
        return new ComplexNumber(
                num1.real() + num2.real(), num1.imag() + num2.imag());
    }

    public ComplexNumber subtract(ComplexNumber num1, ComplexNumber num2)
    {
        logger.info("subtract method");
        return new ComplexNumber(
                num1.real() - num2.real(), num1.imag() - num2.imag());
    }

    public ComplexNumber multiply(ComplexNumber num1, ComplexNumber num2)
    {
        logger.info("multiply method");
        if (num1.imag() == 0. || num2.imag() == 0.)
        {
            return new ComplexNumber(num1.real()*num2.real());
        }

        return new ComplexNumber(
                (num1.real()*num2.real()) - (num1.imag()* num2.imag()),
                (num1.real()*num2.imag()) + (num1.imag()* num2.real()));
    }

    public ComplexNumber divide(ComplexNumber num1, ComplexNumber num2)
    {
        logger.info("divide method");
        double c = num2.real();
        double d = num2.imag();

        // check for Re,Im(z) == 0 to avoid +/-infinity in result
        double zreal2 = 0.0;
        if (c != 0.0)
        {
            zreal2 = StrictMath.pow(c, 2.);
        }
        double zimag2 = 0.0;
        if (d != 0.0)
        {
            zimag2 = StrictMath.pow(d, 2.);
        }

        double ac = num1.real()*c;
        double bd = num1.imag()*d;
        double bc = num1.imag()*c;
        double ad = num1.real()*d;

        return new ComplexNumber((ac+bd)/(zreal2+zimag2),(bc-ad)/(zreal2+zimag2));
    }

    public boolean equals(ComplexNumber num1, ComplexNumber num2)
    {
        logger.info("equals method");
        if (num1.real() == num2.real() && num1.imag() == num2.imag())
        {
            return true;
        }
        return false;
    }
}
