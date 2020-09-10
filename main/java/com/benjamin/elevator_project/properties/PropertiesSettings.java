package com.benjamin.elevator_project.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Team 3
 * @version 1.0.0
 * @since 2020-09-09
 *
 */

public class PropertiesSettings {
	private static int numberOfElevators;
	private static int numberOfFloors;
	private static String nextRequest;
	private static String srcDest;
	private static int metric;

	/**
	 * This method initiates the properties based on the properties file
	 * 
	 * @param filename : String
	 */
	public static void setProperties(String filename) {

		Properties properties = new Properties();
		try {
			FileInputStream inputStream = new FileInputStream(filename);
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		numberOfElevators = Integer.parseInt(properties.getProperty("lift"));
		numberOfFloors = Integer.parseInt(properties.getProperty("level"));
		nextRequest = properties.getProperty("delimiterPassenger");
		srcDest = properties.getProperty("delimeterSrcDest");
		metric = Integer.parseInt(properties.getProperty("metrics"));

	}

	/**
	 * This method returns the total number of elevators.
	 * 
	 * @return numberOfElevators : int
	 */
	public static int getNumberOfElevators() {
		return numberOfElevators;
	}

	/**
	 * This method returns the total number of floors.
	 * 
	 * @return numberOffloors : int
	 */
	public static int getNumberOfFloors() {
		return numberOfFloors;
	}

	/**
	 * This method returns the delimiter that indicates the next request.
	 * 
	 * @return nextRequest : String
	 */
	public static String getNextRequest() {
		return nextRequest;
	}

	/**
	 * This method returns the delimiter that separates the source and destination.
	 * 
	 * @return srcDest : String
	 */
	public static String getSrcDest() {
		return srcDest;
	}

	/**
	 * This method returns the metrics specified by the user that indicates the
	 * optimiser(s) chosen.
	 * 
	 * @return metric : int
	 */
	public static int getMetric() {
		return metric;
	}
}
