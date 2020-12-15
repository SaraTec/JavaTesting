package com.qmetry.testng_demo;

import com.qmetry.NumberSystemsConverter;
import com.qmetry.models.Number;
import com.qmetry.models.NumbericSystem;
import com.qmetry.testng_demo.DataProviders.NumberSystemsConverterTestDataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;

public class NumberSystemsConverterTest {
    public static NumberSystemsConverter converter;

    @BeforeClass
    public static void initConverter(){
        converter = new NumberSystemsConverter();
    }

    @Test(dataProvider="testDecimalToHexConvertData", dataProviderClass = NumberSystemsConverterTestDataProvider.class)
    public static void convertIntToHexTest(int inputNumber, Number expected){
        Number result = converter.convertIntToHex(inputNumber);

        assertEquals(expected.getValue(), result.getValue());
        assertEquals(expected.getSystem(), result.getSystem());
    }

    @Test
    public static void convertIntToBinTest(){
        Number result = converter.convertIntToBin(1133);
        Number expected = new Number("10001101101", NumbericSystem.binary);

        assertEquals(expected.getValue(), result.getValue());
        assertEquals(expected.getSystem(), result.getSystem());
    }
}
