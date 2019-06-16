package com.fxcalculator.console.tool.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fxcalculator.console.tool.FxCalculatorConsoleTool;

public class FxCalculatorConsoleToolTest {

	private List<String> inputList = null;
	private final String[] currencyArray = new String[] { "AUD", "CAD", "CNY", "CZK", "DKK", "EUR", "GBP", "JPY", "NOK", "NZD", "USD" };

	@Before
	public void before() {
		inputList = new ArrayList<>(currencyArray.length * currencyArray.length);
		for (String fromCurrency : currencyArray) {
			for (String toCurrency : currencyArray) {
				inputList.add(String.format("%s 1.00 in %s", fromCurrency, toCurrency));
			}
		}
	}

	@Test
	public void testParseRequestAndPrint() {
		try {
			FxCalculatorConsoleTool tool = new FxCalculatorConsoleTool();
			int count = 0;
			for (String line : inputList) {
				System.out.println("Running for: " + line);
				tool.parseRequestAndPrint(line);
				count++;
			}
			Assert.assertTrue("Total test ran : " + count, count == currencyArray.length * currencyArray.length);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
