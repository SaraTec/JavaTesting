package com.qmetry.testng_demo;

import com.qmetry.ComplexCalculator;
import com.qmetry.models.ComplexNumber;
import com.qmetry.testng_demo.DataProviders.ComplexCalculatorTestDataProvider;
import org.junit.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;

public class ComplexCalculatorTest {
    private static ComplexCalculator calculator;
    private static ComplexNumber n1;
    private static ComplexNumber n2;
    private final double PRECISION = 0.001;
    @BeforeClass
    public static void initCalculator() {
        calculator = new ComplexCalculator();
        n1 = new ComplexNumber(3, 2);
        n2  = new ComplexNumber(3, 6);
    }
    @AfterClass
    public static void afterClassMethod() {
        System.out.println("This is executed after all tests in class");
    }
    @Test(dataProvider = "testEqualsData", dataProviderClass= ComplexCalculatorTestDataProvider.class)
    public void equals(ComplexNumber n1, ComplexNumber n2, Boolean expectedResult){
        Boolean result = calculator.equals(n1, n2);
        assertEquals(expectedResult, result);
    }

    @Test
    public void add(){
        ComplexNumber result = calculator.add(n1, n2);

        assertEquals(6, result.real(), PRECISION);
        assertEquals(8, result.imag(), PRECISION);
    }
    @Test
    public void subtract(){
        ComplexNumber result = calculator.subtract(n1, n2);
        assertEquals(0, result.real(), PRECISION);
        assertEquals(-4, result.imag(), PRECISION);
    }
    @Test
    public void multiply(){
        ComplexNumber result = calculator.multiply(n1, n2);

        assertEquals(-3, result.real(), PRECISION);
        assertEquals(24, result.imag(), PRECISION);
    }
    @Test
    public void divide(){
        ComplexNumber result = calculator.divide(n1, n2);
        assertEquals(0.466, result.real(), PRECISION);
        assertEquals(-0.266, result.imag(), PRECISION);
    }
}
