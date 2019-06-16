package com.fx.provider;

public class FxRateProviderException extends Exception {

	private static final long serialVersionUID = 3734846952085282818L;

	public FxRateProviderException() {
		super();
	}

	public FxRateProviderException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FxRateProviderException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public FxRateProviderException(final String message) {
		super(message);
	}

	public FxRateProviderException(final Throwable cause) {
		super(cause);
	}

}
