package com.properties;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class PropertiesManagerFactory {
	private static volatile IPropertiesManager instance = null;
	private static Lock lock = new ReentrantLock();

	private PropertiesManagerFactory() {
	}

	@SuppressWarnings("unchecked")
	public static IPropertiesManager getPropertiesManager() {
		if (null == instance) {
			lock.lock();
			try {
				if (null == instance) {
					String className = "com.properties.file.impl.PropertiesManagerFileImpl";
					Class<IPropertiesManager> clazz = (Class<IPropertiesManager>) Class.forName(className);
					instance = clazz.newInstance();
				}
			} catch (ReflectiveOperationException ignored) {
			} finally {
				lock.unlock();
			}
		}
		return instance;
	}

}
