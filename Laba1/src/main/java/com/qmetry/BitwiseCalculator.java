package com.qmetry;

import org.apache.log4j.Logger;

public class BitwiseCalculator {
    final static Logger logger = Logger.getLogger(BitwiseCalculator.class);

    public int shiftLeft(int numb, int shift){
        logger.info("shiftLeft method");
        return numb<<shift;
    }
    public int shiftRight(int numb, int shift){
        logger.info("shiftRight method");
        return numb>>shift;
    }
    public int and(int numb1, int numb2){
        logger.info("and method");
        return numb1 & numb2;
    }
    public int or(int numb1, int numb2) {
        logger.info("or method");
        return numb1 | numb2;
    }
    public int xor(int numb1, int numb2){
        logger.info("xor method");
        return numb1^numb2;
    }
}
