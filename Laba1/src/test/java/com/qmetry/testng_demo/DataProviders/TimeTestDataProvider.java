package com.qmetry.testng_demo.DataProviders;

import com.qmetry.Time;
import com.qmetry.models.Number;
import com.qmetry.models.NumbericSystem;
import org.testng.annotations.DataProvider;

public class TimeTestDataProvider {
    @DataProvider(name = "testSetSecondsData")
    public static Object[][] testSetSecondsData() throws Exception {
        return new Object[][]{
                {1432, new Time(0,23,52)},
                {0, new Time(0,0,0)},
                {14329, new Time(3,58,49)},
        };
    }
}
