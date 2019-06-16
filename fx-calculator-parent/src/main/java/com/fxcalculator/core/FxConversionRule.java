package com.fxcalculator.core;

import java.text.DecimalFormat;

public class FxConversionRule {
	public enum FxConversionRuleType {
		UNITY("1:1"),
		DIRECT_FEED("D"),
		DERIEVED("None"),
		INVERTED("Inv");
		private final String code;

		FxConversionRuleType(final String code) {
			this.code = code;
		}

		public String getCode() {
			return code;
		}

		public static FxConversionRuleType fromCode(final String code) {
			FxConversionRuleType[] values = FxConversionRuleType.values();
			for (FxConversionRuleType value : values) {
				if (value.code.equals(code)) {
					return value;
				}
			}
			return DERIEVED;
		}
	}

	private final String fromCurrency;
	private final String toCurrency;
	private final FxConversionRuleType fxConversionRuleType;
	private final String crossViaCurrency;
	private final int fromCurrencyPrecision;
	private final int toCurrencyPrecision;
	private final DecimalFormat fromCurrencyDecimalFormat;
	private final DecimalFormat toCurrencyDecimalFormat;

	private FxConversionRule(final Builder builder) {
		fromCurrency = builder.fromCurrency;
		toCurrency = builder.toCurrency;
		fxConversionRuleType = builder.fxConversionRuleType;
		crossViaCurrency = builder.crossViaCurrency;
		fromCurrencyPrecision = builder.fromCurrencyPrecision;
		toCurrencyPrecision = builder.toCurrencyPrecision;
		fromCurrencyDecimalFormat = builder.fromCurrencyDecimalFormat;
		toCurrencyDecimalFormat = builder.toCurrencyDecimalFormat;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public String getFromCurrency() {
		return fromCurrency;
	}

	public String getToCurrency() {
		return toCurrency;
	}

	public FxConversionRuleType getFxConversionRuleType() {
		return fxConversionRuleType;
	}

	public String getCrossViaCurrency() {
		return crossViaCurrency;
	}

	public int getFromCurrencyPrecision() {
		return fromCurrencyPrecision;
	}

	public int getToCurrencyPrecision() {
		return toCurrencyPrecision;
	}

	public DecimalFormat getFromCurrencyDecimalFormat() {
		return fromCurrencyDecimalFormat;
	}

	public DecimalFormat getToCurrencyDecimalFormat() {
		return toCurrencyDecimalFormat;
	}

	@Override
	public String toString() {
		StringBuilder builder2 = new StringBuilder();
		builder2.append("FxConversionRule [fromCurrency=");
		builder2.append(fromCurrency);
		builder2.append(", toCurrency=");
		builder2.append(toCurrency);
		builder2.append(", fxConversionRuleType=");
		builder2.append(fxConversionRuleType);
		builder2.append(", crossViaCurrency=");
		builder2.append(crossViaCurrency);
		builder2.append(", fromCurrencyPrecision=");
		builder2.append(fromCurrencyPrecision);
		builder2.append(", toCurrencyPrecision=");
		builder2.append(toCurrencyPrecision);
		builder2.append(", fromCurrencyDecimalFormat=");
		builder2.append(fromCurrencyDecimalFormat);
		builder2.append(", toCurrencyDecimalFormat=");
		builder2.append(toCurrencyDecimalFormat);
		builder2.append("]");
		return builder2.toString();
	}

	public static final class Builder {
		private String fromCurrency;
		private String toCurrency;
		private FxConversionRuleType fxConversionRuleType;
		private String crossViaCurrency;
		private int fromCurrencyPrecision;
		private int toCurrencyPrecision;
		private DecimalFormat fromCurrencyDecimalFormat;
		private DecimalFormat toCurrencyDecimalFormat;

		private Builder() {
		}

		public Builder setFromCurrency(final String fromCurrency) {
			this.fromCurrency = fromCurrency;
			return this;
		}

		public Builder setToCurrency(final String toCurrency) {
			this.toCurrency = toCurrency;
			return this;
		}

		public Builder setFxConversionRuleType(final FxConversionRuleType fxConversionRuleType) {
			this.fxConversionRuleType = fxConversionRuleType;
			return this;
		}

		public Builder setCrossViaCurrency(final String crossViaCurrency) {
			this.crossViaCurrency = crossViaCurrency;
			return this;
		}

		public Builder setFromCurrencyPrecision(final int fromCurrencyPrecision) {
			this.fromCurrencyPrecision = fromCurrencyPrecision;
			return this;
		}

		public Builder setToCurrencyPrecision(final int toCurrencyPrecision) {
			this.toCurrencyPrecision = toCurrencyPrecision;
			return this;
		}

		public Builder setFromCurrencyDecimalFormat(final DecimalFormat fromCurrencyDecimalFormat) {
			this.fromCurrencyDecimalFormat = fromCurrencyDecimalFormat;
			return this;
		}

		public Builder setToCurrencyDecimalFormat(final DecimalFormat toCurrencyDecimalFormat) {
			this.toCurrencyDecimalFormat = toCurrencyDecimalFormat;
			return this;
		}

		public FxConversionRule build() {
			return new FxConversionRule(this);
		}
	}

}
