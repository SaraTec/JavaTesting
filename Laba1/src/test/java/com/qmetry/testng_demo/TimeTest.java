package com.qmetry.testng_demo;

import com.qmetry.Time;
import com.qmetry.TimeCalculator;
import com.qmetry.testng_demo.DataProviders.TimeTestDataProvider;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class TimeTest {
	private static Time time;
	private static final double PRECISION = 0.001;
	@BeforeClass
	public static void initCalculator() {
		time = new Time();
	}

	@BeforeTest
	public void beforeEachTest() {
		//System.out.println("This is executed before each Test");
	}

	@AfterTest
	public void afterEachTest() {
		//System.out.println("This is exceuted after each Test");
	}

	@Test(dataProvider = "testSetSecondsData", dataProviderClass= TimeTestDataProvider.class)
	public void testSetSeconds(int inputSeconds, Time expectedResult) {
		try {
			time.setSeconds(inputSeconds);
			assertEqualsTime(expectedResult, time);
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}

	@Test
	public void testSetMinutes() throws Exception{
		int inputMinutes = 3443;
		Time expectedResult = new Time(57,23,0);
		time.setMinutes(inputMinutes);
		assertEqualsTime(expectedResult, time);
	}

	@Test(expectedExceptions = Exception.class)
	public void testSetMinutesException() throws Exception{
		int inputMinutes = -3443;
		time.setMinutes(inputMinutes);
	}

	@Test
	public void testSetHours() throws Exception{
		int inputHours = 23;
		Time expectedResult = new Time(23,0,0);
		time.setHours(inputHours);
		assertEqualsTime(expectedResult, time);
	}

	@Test
	public void getAllSecondsTest() throws Exception{
		Time input = new Time(2,3,30);
		double expectedResult = 7410;
		double result = input.getAllSeconds();
		assertEquals(expectedResult, result, PRECISION);
	}

	@Test
	public void getAllMinuteTest() throws Exception{
		Time input = new Time(2,3,30);
		double expectedResult = 123.5;
		double result = input.getAllMinutes();
		assertEquals(expectedResult, result, PRECISION);
	}
	@Test
	public void getAllHoursTest() throws Exception{
		Time input = new Time(2,30,30);
		double expectedResult = 2.5083;
		double result = input.getAllHours();
		assertEquals(expectedResult, result, PRECISION);
	}
	private void assertEqualsTime(Time t1, Time t2){
		assertEquals(t1.getSeconds(), t2.getSeconds());
		assertEquals(t1.getMinutes(), t2.getMinutes());
		assertEquals(t1.getHours(), t2.getHours());
	}
}
