package com.fxcalculator;

public class FxCalculatorException extends Exception {

	private static final long serialVersionUID = 2039976393229640407L;

	public FxCalculatorException() {
		super();
	}

	public FxCalculatorException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FxCalculatorException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public FxCalculatorException(final String message) {
		super(message);
	}

	public FxCalculatorException(final Throwable cause) {
		super(cause);
	}

}
