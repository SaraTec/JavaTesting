package com.qmetry.testng_demo.DataProviders;
import org.testng.annotations.DataProvider;

public class SimpleCalculatorTestDataProvider {
    @DataProvider(name = "testDivisionData")
    public static Object[][] testDivisionData() {
        return new Object[][]{
                {10, 2, 5, 0.001},
                {10, -2, -5, 0.001},
                {0, 0, 0, 0.0000001},
                {9.3, 2, 4.65, 0.000001},
        };
    }
}
