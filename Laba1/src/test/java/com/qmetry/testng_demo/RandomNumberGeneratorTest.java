package com.qmetry.testng_demo;

import com.qmetry.ISimpleCalculator;
import com.qmetry.RandomNumbersGenerator;
import com.qmetry.SimpleCalculator;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.junit.Assert.*;

public class RandomNumberGeneratorTest {
    private static RandomNumbersGenerator randGenerator;

    @BeforeClass
    public static void initCalculator() {
        randGenerator = new RandomNumbersGenerator();
    }

    @BeforeTest
    public void beforeEachTest() {
        //System.out.println("This is executed before each Test");
    }

    @AfterTest
    public void afterEachTest() {
        //System.out.println("This is exceuted after each Test");
    }

    @Test
    public void testDoubleRandNumbGeneratorWithinRange() {
        double max = 6.7;
        double min = -5.4;
        double result = randGenerator.randDouble(min, max);
        assertTrue(result >= min && result <= max);
    }
    @Test
    public void testIntRandNumbGeneratorWithinRange() {
        int max = 6;
        int min = -5;
        double result = randGenerator.randInt(min, max);
        assertTrue(result >= min && result <= max);
    }
}
