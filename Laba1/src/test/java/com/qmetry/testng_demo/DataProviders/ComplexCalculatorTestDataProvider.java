package com.qmetry.testng_demo.DataProviders;
import com.qmetry.models.ComplexNumber;
import org.testng.annotations.DataProvider;

public class ComplexCalculatorTestDataProvider {
    @DataProvider(name = "testEqualsData")
    public static Object[][] testEqualsData() {
        return new Object[][]{
                {new ComplexNumber(10,2 ), new ComplexNumber(10,4 ), false},
                {new ComplexNumber(10,2 ), new ComplexNumber(10,2 ), true},
                {new ComplexNumber(10,2 ), new ComplexNumber(2,10 ), false},
                {new ComplexNumber(-10), new ComplexNumber(-10,0 ), true},
        };
    }
}
