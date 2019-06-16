package com.fx.provider;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.properties.IPropertiesManager;
import com.properties.PropertiesManagerFactory;

public final class FxRateProviderFactory {
	private static volatile IFxRateProvider instance = null;
	private static Lock lock = new ReentrantLock();
	private static IPropertiesManager propertiesManager = PropertiesManagerFactory.getPropertiesManager();

	private FxRateProviderFactory() {
	}

	/**
	 * Provides the handle of FxRateProvider. Based of the configuration it will
	 * initialize the implementation and returns the reference In this particular
	 * case it returns a File implementation of Fx Rate Provider but in real world
	 * scenario it can connect any market data provider like WIMCO / Reuters /
	 * Bloomberg etc.
	 *
	 * @return IFxRateProvider
	 * @throws FxRateProviderException
	 */
	@SuppressWarnings("unchecked")
	public static IFxRateProvider getFxRateProvider() throws FxRateProviderException {
		if (null == instance) {
			lock.lock();
			try {
				if (null == instance) {
					String className = propertiesManager.getStringValue("fx.provider.impl.class.name", "com.fx.provider.file.impl.FxRateProviderFileImpl");
					Class<IFxRateProvider> clazz = (Class<IFxRateProvider>) Class.forName(className);
					instance = clazz.newInstance();
				}
			} catch (Exception e) {
				throw new FxRateProviderException("Got exception while getting fx rate provider", e);
			} finally {
				lock.unlock();
			}
		}
		return instance;
	}

}
