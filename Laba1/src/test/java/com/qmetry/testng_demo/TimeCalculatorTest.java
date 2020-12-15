package com.qmetry.testng_demo;

import com.qmetry.Time;
import com.qmetry.TimeCalculator;
import com.qmetry.testng_demo.DataProviders.TimeCalculatorTestDataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;

public class TimeCalculatorTest {
    private static TimeCalculator calculator;

    @BeforeClass
    public static void initCalculator(){
        calculator = new TimeCalculator();
    }

    @Test(dataProvider="sumTestData", dataProviderClass = TimeCalculatorTestDataProvider.class)
    public void testSum(Time inputTime1,Time inputTime2, Time expectedResult)throws Exception{
        Time result = calculator.sum(inputTime1, inputTime2);
        assertEqualsTime(expectedResult, result);
    }

    @Test(dataProvider="differenceTestData", dataProviderClass = TimeCalculatorTestDataProvider.class)
    public void testDifference(Time inputTime1, Time inputTime2, Time expectedResult) throws Exception {
        Time result = calculator.difference(inputTime1, inputTime2);
        assertEqualsTime(expectedResult, result);
    }

    @Test(dataProvider="equalsTestData", dataProviderClass = TimeCalculatorTestDataProvider.class)
    public void testEquals(Time inputTime1,Time inputTime2, Boolean expectedResult){
        Boolean result = calculator.equal(inputTime1, inputTime2);
        assertEquals(expectedResult, result);
    }

    private void assertEqualsTime(Time t1, Time t2){
        assertEquals(t1.getSeconds(), t2.getSeconds());
        assertEquals(t1.getMinutes(), t2.getMinutes());
        assertEquals(t1.getHours(), t2.getHours());
    }
}
