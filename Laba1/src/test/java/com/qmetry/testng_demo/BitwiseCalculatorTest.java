package com.qmetry.testng_demo;

import com.qmetry.BitwiseCalculator;
import org.junit.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BitwiseCalculatorTest {
    private static BitwiseCalculator calculator;

    @BeforeClass
    public static void initCalculator() {
        System.out.println("This is executed before all tests in class");
        calculator = new BitwiseCalculator();
    }

    @AfterClass
    public static void afterClassMethod() {
        System.out.println("This is executed after all tests in class");
    }

    @Test(threadPoolSize = 2, invocationCount = 2)
    public void shiftLeft(){
        int result = calculator.shiftLeft(3, 4);
        assertEquals(48, result);
    }
    @Test
    public void shiftRight(){
        int result = calculator.shiftRight(100, 4);
        assertEquals(6, result);
    }
    @Test
    public void and(){
        int result = calculator.and(10, 13);
        assertEquals(8, result);
    }
    @Test
    public void or(){
        int result = calculator.or(10, 13);
        assertEquals(15, result);
    }
    @Test
    public void xor(){
        int result = calculator.xor(10, 13);
        assertEquals(7, result);
    }
}
