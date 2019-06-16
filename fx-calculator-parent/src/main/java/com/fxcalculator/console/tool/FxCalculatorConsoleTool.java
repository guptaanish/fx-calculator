package com.fxcalculator.console.tool;

import java.util.Scanner;

import com.fxcalculator.FxCalculator;
import com.fxcalculator.FxCalculatorException;
import com.fxcalculator.config.FxCalculatorMatrixException;
import com.fxcalculator.config.FxCalculatorMatrixFactory;
import com.fxcalculator.core.FxConversionRule;
import com.fxcalculator.core.InvalidFxPairException;
import com.fxcalculator.util.FxUitl;

public class FxCalculatorConsoleTool {
	public static void main(final String[] args) {
		FxCalculatorConsoleTool tool = new FxCalculatorConsoleTool();
		try (Scanner scanner = new Scanner(System.in)) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				tool.parseRequestAndPrint(line);
			}
		}
	}

	public void parseRequestAndPrint(final String line) {
		String[] split = line.split(" ");
		if (split.length != 4) {
			System.out.println("Invalid input");
			return;
		}
		double amount = Double.parseDouble(split[1].trim());
		String fromCurrency = split[0].trim();
		String toCurrency = split[3].trim();
		try {
			double convertedAmount = FxCalculator.getInstance().convert(amount, fromCurrency, toCurrency);
			FxConversionRule rule = FxCalculatorMatrixFactory.getFxCalculatorConfig().getRuleForFxPair(fromCurrency, toCurrency);
			String fromAmountString = FxUitl.format(amount, rule.getFromCurrencyDecimalFormat());
			String convertedAmountString = FxUitl.format(convertedAmount, rule.getToCurrencyDecimalFormat());
			System.out.println(String.format("%s %s = %s %s", fromCurrency, fromAmountString, toCurrency, convertedAmountString));
		} catch (FxCalculatorException | FxCalculatorMatrixException e) {
			e.printStackTrace();
		} catch (InvalidFxPairException e) {
			System.out.println(e.getMessage());
		}

	}
}