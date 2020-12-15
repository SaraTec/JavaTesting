package com.qmetry.testng_demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.qmetry.testng_demo.DataProviders.SimpleCalculatorTestDataProvider;
import org.testng.annotations.*;

import com.qmetry.SimpleCalculator;
import com.qmetry.ISimpleCalculator;


public class SimpleCalculatorTest {
	private static ISimpleCalculator calculator;

	@BeforeClass
	public static void initCalculator() {
		calculator = new SimpleCalculator();
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
	public void testSum() {
		double result = calculator.sum(3, 4);
		assertEquals(7, result, 0.001);
	}

	@Test(dataProvider = "testDivisionData", dataProviderClass= SimpleCalculatorTestDataProvider.class)
	public void testDivison(double num1, double num2, double expectedResult, double delta) {
		try {
			double result = calculator.divison(num1, num2);

			assertEquals(expectedResult, result, delta);
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}

	@Test(expectedExceptions = Exception.class)
	public void testDivisionException() throws Exception {
		calculator.divison(10, 0);
	}

	@Test
	public void testEqual() {
		boolean result = calculator.equalIntegers(20, 20);
		assertTrue(result);
	}

	@Test
	public void testSubstraction() {
		double result = calculator.subtraction(10, 3);
		assertTrue(result == 7);
	}
	@Test
	public void testMultiplication() {
		double result = calculator.multiplication(10, 3);
		assertTrue(result == 30);
	}

}
