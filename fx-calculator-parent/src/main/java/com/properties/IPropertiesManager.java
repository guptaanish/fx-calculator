package com.properties;

public interface IPropertiesManager {
	Object get(String propertyName);

	String getStringValue(String propertyName, String defaultValue);

	int getIntegerValue(String propertyName, int defaultValue);

	long getLongValue(String propertyName, long defaultValue);

	double getDoubleValue(String propertyName, double defaultValue);

	boolean getBooleanValue(String propertyName, boolean defaultValue);

	StringBuilder getDump();
}
