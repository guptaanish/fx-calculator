package com.fxcalculator.config.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.fxcalculator.config.FxCalculatorMatrixException;
import com.fxcalculator.config.IFxCalculatorMatrix;
import com.fxcalculator.core.FxConversionRule;
import com.fxcalculator.core.FxConversionRule.Builder;
import com.fxcalculator.core.FxConversionRule.FxConversionRuleType;
import com.fxcalculator.util.FxUitl;

public class FxCalculatorMatrixImpl implements IFxCalculatorMatrix {
	private final Map<String, FxConversionRule> fxMatrix = new HashMap<>();

	public FxCalculatorMatrixImpl() throws FxCalculatorMatrixException {
		buildMatrix();
	}

	private Map<String, Integer> buildCurrencyPrecisionMap() throws FileNotFoundException {
		try (Scanner scanner = new Scanner(new File(getClass().getClassLoader().getResource("fx-formating.properties").getFile()))) {
			Map<String, Integer> map = new HashMap<>();
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] tokens = line.split("=");
				map.put(tokens[0], Integer.parseInt(tokens[1].trim()));
			}
			return map;
		}
	}

	@Override
	public Map<String, FxConversionRule> buildAndGetFxRateMatrix() throws FxCalculatorMatrixException {
		return fxMatrix;
	}

	private Map<String, FxConversionRule> buildMatrix() throws FxCalculatorMatrixException {
		try (Scanner scanner = new Scanner(new File(getClass().getClassLoader().getResource("fx-matrix.csv").getFile()))) {
			Map<String, Integer> currencyPrecisionMap = buildCurrencyPrecisionMap();
			String[] toCurrencyList = null;
			if (scanner.hasNextLine()) {
				String headerLine = scanner.nextLine();
				toCurrencyList = headerLine.split(",");
			}
			List<FxConversionRule> ruleList = new ArrayList<>();
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] tokens = line.split(",");
				String fromCurrency = tokens[0];
				for (int i = 1; i < tokens.length; i++) {
					FxConversionRuleType fxConversionRuleType = FxConversionRuleType.fromCode(tokens[i]);
					String crossViaCurrency = null;
					if (FxConversionRuleType.DERIEVED == fxConversionRuleType) {
						crossViaCurrency = tokens[i];
					}
					String toCurrency = toCurrencyList[i];
					Builder builder = FxConversionRule.newBuilder();
					builder.setCrossViaCurrency(crossViaCurrency);
					builder.setFromCurrency(fromCurrency);
					builder.setToCurrency(toCurrency);
					builder.setFxConversionRuleType(fxConversionRuleType);
					builder.setFromCurrencyPrecision(currencyPrecisionMap.get(fromCurrency));
					builder.setToCurrencyPrecision(currencyPrecisionMap.get(toCurrency));
					builder.setFromCurrencyDecimalFormat(FxUitl.createDecimalFormat(currencyPrecisionMap.get(fromCurrency)));
					builder.setToCurrencyDecimalFormat(FxUitl.createDecimalFormat(currencyPrecisionMap.get(toCurrency)));
					ruleList.add(builder.build());
				}
			}
			for (FxConversionRule rule : ruleList) {
				fxMatrix.put(FxUitl.constructFxPairKey(rule.getFromCurrency(), rule.getToCurrency()), rule);
			}
			return Collections.unmodifiableMap(fxMatrix);
		} catch (Exception e) {
			throw new FxCalculatorMatrixException("Got exception while loading Fx Matrix", e);
		}
	}

	@Override
	public FxConversionRule getRuleForFxPair(final String fromCurrency, final String toCurrency) throws FxCalculatorMatrixException {
		return fxMatrix.get(FxUitl.constructFxPairKey(fromCurrency, toCurrency));
	}
}
