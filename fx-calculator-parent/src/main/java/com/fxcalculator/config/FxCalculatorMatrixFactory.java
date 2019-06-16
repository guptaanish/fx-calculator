package com.fxcalculator.config;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.properties.IPropertiesManager;
import com.properties.PropertiesManagerFactory;

public final class FxCalculatorMatrixFactory {
	private static volatile IFxCalculatorMatrix instance = null;
	private static Lock lock = new ReentrantLock();
	private static IPropertiesManager propertiesManager = PropertiesManagerFactory.getPropertiesManager();

	private FxCalculatorMatrixFactory() {
	}

	@SuppressWarnings("unchecked")
	public static IFxCalculatorMatrix getFxCalculatorConfig() throws FxCalculatorMatrixException {
		if (null == instance) {
			lock.lock();
			try {
				if (null == instance) {
					String className = propertiesManager.getStringValue("fx.calculator.config.impl.class.name", "com.fxcalculator.config.impl.FxCalculatorMatrixImpl");
					Class<IFxCalculatorMatrix> clazz = (Class<IFxCalculatorMatrix>) Class.forName(className);
					instance = clazz.newInstance();
				}
			} catch (Exception e) {
				throw new FxCalculatorMatrixException("Got exception while fx calculator config", e);
			} finally {
				lock.unlock();
			}
		}
		return instance;
	}

}
