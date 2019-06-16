package com.fxcalculator;

import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.fx.provider.FxRateProviderException;
import com.fx.provider.FxRateProviderFactory;
import com.fx.provider.IFxRateProvider;
import com.fxcalculator.config.FxCalculatorMatrixException;
import com.fxcalculator.config.FxCalculatorMatrixFactory;
import com.fxcalculator.core.FxConversionRule;
import com.fxcalculator.core.InvalidFxPairException;
import com.fxcalculator.util.FxUitl;

public class FxCalculator {
	private static volatile FxCalculator instance = null;
	private static Lock lock = new ReentrantLock();
	private final Map<String, FxConversionRule> fxMatrix;
	private final IFxRateProvider fxRateProvider;

	private FxCalculator() throws FxCalculatorException {
		try {
			fxRateProvider = FxRateProviderFactory.getFxRateProvider();
			fxMatrix = FxCalculatorMatrixFactory.getFxCalculatorConfig().buildAndGetFxRateMatrix();
		} catch (FxRateProviderException | FxCalculatorMatrixException e) {
			throw new FxCalculatorException("Got exception while initializing fx calculator", e);
		}
	}

	public static FxCalculator getInstance() throws FxCalculatorException {
		if (null == instance) {
			lock.lock();
			try {
				if (null == instance) {
					instance = new FxCalculator();
				}
			} catch (FxCalculatorException e) {
				throw new FxCalculatorException("Got exception while initializing fx calculator", e);
			} finally {
				lock.unlock();
			}
		}
		return instance;
	}

	public double convert(final double amount, final String fromCurrency, final String toCurrency) throws FxCalculatorException, InvalidFxPairException {
		String key = FxUitl.constructFxPairKey(fromCurrency, toCurrency);
		FxConversionRule fxConversionRule = fxMatrix.get(key);
		if (null == fxConversionRule) {
			throw new InvalidFxPairException(String.format("Unable to find rate for %s/%s", fromCurrency, toCurrency));
		}
		return traverseFxChainAndGetRate(amount, fromCurrency, toCurrency);
	}

	private double traverseFxChainAndGetRate(final double amount, final String fromCurrency, final String toCurrency) throws FxCalculatorException, InvalidFxPairException {
		String key = FxUitl.constructFxPairKey(fromCurrency, toCurrency);
		FxConversionRule fxConversionRule = fxMatrix.get(key);
		try {
			double convertedAmount = 0.0;
			switch (fxConversionRule.getFxConversionRuleType()) {
			case DERIEVED:
				double rate = traverseFxChainAndGetRate(1, fxConversionRule.getFromCurrency(), fxConversionRule.getCrossViaCurrency());
				convertedAmount = amount * rate * traverseFxChainAndGetRate(1, fxConversionRule.getCrossViaCurrency(), fxConversionRule.getToCurrency());
				break;
			case DIRECT_FEED:
				convertedAmount = amount * fxRateProvider.getFxRate(fromCurrency, toCurrency);
				break;
			case INVERTED:
				convertedAmount = amount * 1 / traverseFxChainAndGetRate(1, toCurrency, fromCurrency);
				break;
			case UNITY:
				convertedAmount = amount;
				break;
			default:
				break;
			}
			return convertedAmount;
		} catch (FxRateProviderException e) {
			throw new FxCalculatorException("Got exception while initializing Fx calculator", e);
		}
	}
}
