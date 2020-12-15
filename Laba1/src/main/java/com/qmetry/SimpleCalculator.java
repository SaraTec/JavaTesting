package com.qmetry;

import org.apache.log4j.Logger;

public class SimpleCalculator implements ISimpleCalculator {
	final static Logger logger = Logger.getLogger(SimpleCalculator.class);

	@Override
	public double sum(double a, double b) {
		logger.info("sum method");
		return a + b;
	}

	@Override
	public double subtraction(double a, double b) {
		logger.info("subtraction method");
		return a - b;
	}

	@Override
	public double multiplication(double a, double b) {
		logger.info("multiplication method");
		return a * b;
	}

	@Override
	public double divison(double a, double b) throws Exception {
		logger.info("divison method");
		if (b == 0) {
			throw new Exception("Divider can't be zero");
		}

		return a / b;
	}

	@Override
	public boolean equalIntegers(double a, double b) {
		logger.info("equalIntegers method");

		boolean result = false;
		if (a == b) {
			result = true;
		}

		return result;
	}
}
