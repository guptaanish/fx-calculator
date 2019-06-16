package com.fx.provider.file.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.fx.provider.FxRateProviderException;
import com.fx.provider.IFxRateProvider;
import com.fxcalculator.core.InvalidFxPairException;
import com.fxcalculator.util.FxUitl;

public class FxRateProviderFileImpl implements IFxRateProvider {
	private final Map<String, Double> fxPairVsRate = new HashMap<>();

	public FxRateProviderFileImpl() throws FxRateProviderException {
		try (Scanner scanner = new Scanner(new File(getClass().getClassLoader().getResource("fx-rates.properties").getFile()))) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] tokens = line.split("=");
				fxPairVsRate.put(tokens[0], Double.parseDouble(tokens[1].trim()));
			}
		} catch (FileNotFoundException e) {
			throw new FxRateProviderException("Got exception while loading FxRates from file [fx-rates.properties]", e);
		}
	}

	@Override
	public double getFxRate(final String fromCurrency, final String toCurrency) throws FxRateProviderException, InvalidFxPairException {
		Double rate = fxPairVsRate.get(FxUitl.constructFxPairKey(fromCurrency, toCurrency));
		if (null == rate) {
			throw new InvalidFxPairException(String.format("Unable to find rate for %s/%s", fromCurrency, toCurrency));
		}
		return rate;
	}

}
