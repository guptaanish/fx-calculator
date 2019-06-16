package com.fxcalculator.config;

import java.util.Map;

import com.fxcalculator.core.FxConversionRule;

public interface IFxCalculatorMatrix {
	Map<String, FxConversionRule> buildAndGetFxRateMatrix() throws FxCalculatorMatrixException;

	FxConversionRule getRuleForFxPair(String fromCurrency, String toCurrency) throws FxCalculatorMatrixException;
}
