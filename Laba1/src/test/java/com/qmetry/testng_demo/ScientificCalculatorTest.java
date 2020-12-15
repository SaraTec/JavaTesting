package com.qmetry.testng_demo;

import com.qmetry.ISimpleCalculator;
import com.qmetry.ScientificCalculator;
import com.qmetry.SimpleCalculator;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ScientificCalculatorTest {
	private static ScientificCalculator calculator;
	private static double PRECISION = 0.0001;

	@BeforeClass
	public static void initCalculator() {
		calculator = new ScientificCalculator();
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
	public void testRoot(){
		double inputNumber = 6;
		double inputN = 4;
		double expectedResult = 1.5650;
		double actualResult = calculator.root(inputNumber, inputN);
		assertEquals(expectedResult, actualResult, PRECISION);
	}

	@Test
	public void testPower() {
		double inputNumber = 4;
		int inputN = 7;
		int expectedResult = 16384;
		double actualResult = calculator.power(inputNumber, inputN);
		assertEquals(expectedResult, actualResult, PRECISION);
	}

	@Test
	public void testModulo() {
		double inputNumber1 = 89;
		double inputNumber2  = 3;
		double expectedResult = 2;
		double actualResult = calculator.modulo(inputNumber1, inputNumber2);
		assertEquals(expectedResult, actualResult, PRECISION);
	}

	@Test
	public void testLn() {
		try {
			double inputNumber = 23;
			double expectedResult = 3.1354;
			double actualResult = calculator.ln(inputNumber);
			assertEquals(expectedResult, actualResult, PRECISION);
		}
		catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}

	@Test
	public void testLnException() {
		double result = calculator.ln(-10);
		assertTrue(Double.isNaN(result));
	}

	@Test
	public void testLog() {
		double inputNumber = 259;
		double expectedResult = 2.4132;
		double actualResult = calculator.log(inputNumber);
		assertEquals(expectedResult, actualResult, PRECISION);
	}

	@Test
	public void testLogException(){
		double result = calculator.log(-10);
		assertTrue(Double.isNaN(result));
	}
}
