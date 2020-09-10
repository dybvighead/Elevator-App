package com.benjamin.elevator_project.properties_settings_test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileInputStream;
import java.util.Properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import com.benjamin.elevator_project.properties.PropertiesSettings;

class PropertiesSettingsTest {

	Properties properties;
	FileInputStream inputStream;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		String fileName = "config.properties";
		PropertiesSettings.setProperties(fileName);
		properties = new Properties();
		try {
			inputStream = new FileInputStream(fileName);
			properties.load(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void test_setProperties_returnsNumberOfElevators_WhenConfigFileIsPassedIn() {
		int expected = Integer.parseInt(properties.getProperty("lift"));
		assertEquals(expected, PropertiesSettings.getNumberOfElevators());
	}

	@Test
	void test_setProperties_returnsNumberOfFloors_WhenConfigFileIsPassedIn() {
		int expected = Integer.parseInt(properties.getProperty("level"));
		assertEquals(expected, PropertiesSettings.getNumberOfFloors());
	}

	@Test
	void test_setProperties_returnsNextRequestDelimiter_WhenConfigFileIsPassedIn() {
		String expected = properties.getProperty("delimiterPassenger");
		assertEquals(expected, PropertiesSettings.getNextRequest());
	}

	@Test
	void test_setProperties_returnsSrcDestDelimiter_WhenConfigFileIsPassedIn() {
		String expected = properties.getProperty("delimeterSrcDest");
		assertEquals(expected, PropertiesSettings.getSrcDest());
	}

	@Test
	void test_setProperties_returnsMetrics_WhenConfigFileIsPassedIn() {
		int expected = Integer.parseInt(properties.getProperty("metrics"));
		assertEquals(expected, PropertiesSettings.getMetric());
	}

}
