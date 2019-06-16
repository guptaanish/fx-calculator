package com.fx.provider;

import com.fxcalculator.core.InvalidFxPairException;

/**
 * This interface provide the methods to retrieve the FX rates for a given
 * currency pair
 *
 */
public interface IFxRateProvider {
	double getFxRate(String fromCurrency, String toCurrency) throws FxRateProviderException, InvalidFxPairException;
}
