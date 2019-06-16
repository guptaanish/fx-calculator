package com.properties.file.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.properties.IPropertiesManager;

public class PropertiesManagerFileImpl implements IPropertiesManager {
	private static final Map<String, Object> properties = new HashMap<>();

	public PropertiesManagerFileImpl() {
		try (Scanner scanner = new Scanner(new File(getClass().getClassLoader().getResource("app.properties").getFile()))) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (null == line || line.isEmpty() || line.trim().startsWith("#")) {
					continue;
				}
				String[] tokens = line.split("=");
				properties.put(tokens[0].trim(), tokens[1].trim());
			}
		} catch (FileNotFoundException ignored) {
		}
	}

	@Override
	public Object get(final String propertyName) {
		return properties.get(propertyName);
	}

	@Override
	public String getStringValue(final String propertyName, final String defaultValue) {
		Object value = properties.get(propertyName);
		if (null != value) {
			return value.toString();
		}
		return defaultValue;
	}

	@Override
	public int getIntegerValue(final String propertyName, final int defaultValue) {
		Object value = properties.get(propertyName);
		if (null != value) {
			return Integer.parseInt(value.toString().trim());
		}
		return defaultValue;
	}

	@Override
	public long getLongValue(final String propertyName, final long defaultValue) {
		Object value = properties.get(propertyName);
		if (null != value) {
			return Long.parseLong(value.toString().trim());
		}
		return defaultValue;
	}

	@Override
	public double getDoubleValue(final String propertyName, final double defaultValue) {
		Object value = properties.get(propertyName);
		if (null != value) {
			return Double.parseDouble(value.toString().trim());
		}
		return defaultValue;
	}

	@Override
	public boolean getBooleanValue(final String propertyName, final boolean defaultValue) {
		Object value = properties.get(propertyName);
		if (null != value) {
			return Boolean.parseBoolean(value.toString().trim());
		}
		return defaultValue;
	}

	@Override
	public StringBuilder getDump() {
		return new StringBuilder().append(properties);
	}
}
