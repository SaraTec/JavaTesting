package com.qmetry.testng_demo.DataProviders;

import com.qmetry.models.NumbericSystem;
import com.qmetry.models.Number;
import org.testng.annotations.DataProvider;

public class NumberSystemsConverterTestDataProvider {
    @DataProvider(name = "testDecimalToHexConvertData")
    public static Object[][] testDecimalToHexConvertData() {
        return new Object[][]{
                {1133, new Number("46D", NumbericSystem.hexadecimal)},
                {10, new Number("A", NumbericSystem.hexadecimal)},
                {99, new Number("63", NumbericSystem.hexadecimal)},
        };
    }
}
