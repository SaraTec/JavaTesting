package com.qmetry;

import org.apache.log4j.Logger;

import java.util.Random;

public class RandomNumbersGenerator {
    final static Logger logger = Logger.getLogger(RandomNumbersGenerator.class);

    public int randInt(int min, int max){
        logger.info("randInt method");
        return (int)(Math.random() * ((max - min) + 1)) + min;
    }
    public double randDouble(double min, double max){
        logger.info("randDouble method");
        Random r = new Random();
        return (min + (max - max) * r.nextDouble());
    }
}
