package com.fxcalculator.core;

public class FxEntity {
	private final double amount;
	private final String fromCurrency;
	private final String toCurrency;

	private FxEntity(final Builder builder) {
		amount = builder.amount;
		fromCurrency = builder.fromCurrency;
		toCurrency = builder.toCurrency;
		validate();
	}

	private void validate() {
		if (amount == 0) {
			throw new IllegalArgumentException("Amount cann't be 0");
		}
		if (fromCurrency == null || fromCurrency.isEmpty()) {
			throw new IllegalArgumentException("fromCurrency cann't be null or blank");
		}
		if (toCurrency == null || toCurrency.isEmpty()) {
			throw new IllegalArgumentException("toCurrency cann't be null or blank");
		}
	}

	public double getAmount() {
		return amount;
	}

	public String getFromCurrency() {
		return fromCurrency;
	}

	public String getToCurrency() {
		return toCurrency;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public static final class Builder {
		private double amount;
		private String fromCurrency;
		private String toCurrency;

		private Builder() {
		}

		public Builder setAmount(final double amount) {
			this.amount = amount;
			return this;
		}

		public Builder setFromCurrency(final String fromCurrency) {
			this.fromCurrency = fromCurrency;
			return this;
		}

		public Builder setToCurrency(final String toCurrency) {
			this.toCurrency = toCurrency;
			return this;
		}

		public FxEntity build() {
			return new FxEntity(this);
		}
	}

}
