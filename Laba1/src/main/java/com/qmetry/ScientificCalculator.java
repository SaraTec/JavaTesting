package com.qmetry;

import org.apache.log4j.Logger;

public class ScientificCalculator {
    final static Logger logger = Logger.getLogger(ScientificCalculator.class);

    public double root(double num, double n){
        logger.info("root method");
        return Math.pow(Math.E, Math.log(num)/n);
    }
    public double power(double numb, int n) {
        logger.info("power method");
        return Math.pow(numb, n);
    }
    public double modulo(double numb1, double numb2) {
        logger.info("modulo method");
        return numb1 % numb2;
    }
    public double ln(double numb) {
        logger.info("ln method");
        return Math.log(numb);
    }
    public double log(double numb) {
        logger.info("log method");
        return Math.log10(numb);
    }
}
