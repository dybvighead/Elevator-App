package com.benjamin.elevator_project.request;

import com.benjamin.elevator_project.exceptions.FormatException;
import com.benjamin.elevator_project.exceptions.NegativeException;
import com.benjamin.elevator_project.exceptions.OutOfRangeException;
import com.benjamin.elevator_project.properties.PropertiesSettings;
import com.benjamin.elevator_project.view.View;

/**
 * @author Team 3
 * @version 1.0.0
 * @since 2020-09-09
 *
 */

public class Request {

	private int src;
	private int dest;
	private boolean inputIsInvalid = true;
	private String input;
	private String delimiter;

	/**
	 * This constructor initiates the input string and the delimiter that separates
	 * the individual request.
	 * 
	 * @param input     : String
	 * @param delimiter : String
	 */
	public Request(String input, String delimiter) {
		this.input = input;
		this.delimiter = delimiter;
	}

	/**
	 * This method process the string and returns the boolean value that indicates
	 * the validity of the string input.
	 * 
	 * @return flag : boolean
	 */
	public boolean processString() {
		if (this.input.equalsIgnoreCase("q"))
			exitProgram();

		try {
			String[] input = this.input.split(this.delimiter);
			if (input.length == 1)
				throw new FormatException("Input should be in format src:dest\n");

			src = Integer.parseInt(input[0]);
			dest = Integer.parseInt(input[1]);

			if (src < 1 || dest < 1)
				throw new NegativeException("Input should be positive integer\n");
			else if (src > PropertiesSettings.getNumberOfFloors() || dest > PropertiesSettings.getNumberOfFloors())
				throw new OutOfRangeException("Input is out of range\n");
			else
				inputIsInvalid = false;

		} catch (ArrayIndexOutOfBoundsException e) {
			View.userLogger.error("Input should be in format src:dest\n");
		} catch (NumberFormatException e) {
			View.userLogger.error("Input should only contain numbers\n");
		} catch (NegativeException | OutOfRangeException | FormatException e) {
			View.userLogger.error(e.getMessage());
		} catch (Exception e) {
			View.userLogger.error(e);
		}
		return inputIsInvalid;
	}

	/**
	 * This method exits the program.
	 * 
	 */
	public void exitProgram() {
		View.userLogger.info("Exiting program\n");
		System.exit(1);
	}

	/**
	 * This method gets the string input from the user.
	 * 
	 * @return string : String
	 */
	public String getString() {
		return input;
	}

	/**
	 * This method gets the delimiter that separates the source and destination in
	 * the request.
	 * 
	 * @return delimiter : String
	 */
	public String getDelimiter() {
		return delimiter;
	}

	/**
	 * This method gets the source of the request.
	 * 
	 * @return src : int
	 */
	public int getSrc() {
		return src;
	}

	/**
	 * This method gets the destination of the request.
	 * 
	 * @return dest : int
	 */
	public int getDest() {
		return dest;
	}

	/**
	 * This method returns boolean value that indicates the validity of the input.
	 * 
	 * @return flag : boolean
	 */
	public boolean isFlag() {
		return inputIsInvalid;
	}

	/**
	 * This method sets the boolean value that is used to indicate the validity of
	 * the input. It is used to reset the flag in the controller class to its
	 * default state.
	 * 
	 * @return flag : boolean
	 * @see Controller
	 */
	public void setFlag(boolean flag) {
		this.inputIsInvalid = flag;
	}
}
