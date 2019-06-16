package com.fxcalculator.util;

import java.text.DecimalFormat;

public final class FxUitl {
	private FxUitl() {
	}

	public static String constructFxPairKey(final String fromCurrency, final String toCurrency) {
		return new StringBuilder().append(fromCurrency).append(toCurrency).toString();
	}

	public static String format(final double amount, final DecimalFormat decimalFormat) {
		return decimalFormat.format(amount);
	}

	public static DecimalFormat createDecimalFormat(final int precision) {
		StringBuilder format = new StringBuilder().append("0");
		if (precision > 0) {
			format.append(".");
			for (int i = 0; i < precision; i++) {
				format.append("0");
			}
		}
		return new DecimalFormat(format.toString());
	}

}
