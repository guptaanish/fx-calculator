package com.fxcalculator.core;

public class InvalidFxPairException extends Exception {

	private static final long serialVersionUID = -3751127978870312132L;

	public InvalidFxPairException() {
		super();
	}

	public InvalidFxPairException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidFxPairException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public InvalidFxPairException(final String message) {
		super(message);
	}

	public InvalidFxPairException(final Throwable cause) {
		super(cause);
	}

}
