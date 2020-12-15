package com.qmetry.testng_demo.DataProviders;

import com.qmetry.Time;
import org.testng.annotations.DataProvider;

public class TimeCalculatorTestDataProvider {

    @DataProvider(name="sumTestData")
    public static Object[][] sumData () throws Exception
    {
        return new Object[][]{
                {
                        new Time(1, 4, 5),
                        new Time(1, 7, 50),
                        new Time(2, 11, 55)
                },
                {
                        new Time(1, 4, 35),
                        new Time(1, 7, 50),
                        new Time(2, 12, 25)
                },
                {
                        new Time(1, 52, 35),
                        new Time(1, 7, 50),
                        new Time(3, 0, 25)
                }
        };
    }

    @DataProvider(name="differenceTestData")
    public static Object[][] differenceData() throws Exception
    {
        return new Object[][]{
                {
                        new Time(1, 4, 5),
                        new Time(1, 7, 50),
                        new Time(0, 3, 45)
                },
                {
                        new Time(2, 4, 120),
                        new Time(1, 7, 10),
                        new Time(0, 58, 50)
                },
                {
                        new Time(0, 0, 11000),
                        new Time(0, 7, 50),
                        new Time(2, 55, 30)
                }
        };
    }

    @DataProvider(name="equalsTestData")
    public static Object[][] equalsData() throws Exception
    {
        return new Object[][]{
                {
                        new Time(1, 4, 5),
                        new Time(1, 4, 50),
                        false
                },
                {
                        new Time(1, 0, 120),
                        new Time(1, 2, 0),
                        true
                },
                {
                        new Time(11, 2, 1),
                        new Time(10, 62, 0),
                        false
                }
        };
    }
}
