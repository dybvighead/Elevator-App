package com.benjamin.elevator_project.view;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.benjamin.elevator_project.elevator.Elevator;
import com.benjamin.elevator_project.gui.FrameView;
import com.benjamin.elevator_project.properties.PropertiesSettings;

/**
 * @author Team 3
 * @version 1.0.0
 * @since 2020-09-09
 *
 */

public class View {

	public static final Logger viewLogger = LogManager.getLogger("viewLogger");
	public static final Logger userLogger = LogManager.getLogger("userLogger");

	/**
	 * This method is used to display the welcome message to the user and the input
	 * prompt, as well as initialise the frame view object.
	 * 
	 */
	public void displayWelcome() {
		userLogger.info("Please enter elevator request in the following format - srd{}dest\n",
				PropertiesSettings.getSrcDest());
		userLogger.info("(Input q to quit)\n> ");
		FrameView frameView = new FrameView(PropertiesSettings.getNumberOfFloors());
	}

	/**
	 * This method is used to get the input from the user.
	 * 
	 * @return input : String
	 */
	public String getInput() {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}

	/**
	 * This method is used to display the current floor of the elevator.
	 * 
	 * @param elevator : Elevator
	 */

	public static void displayFloor(Elevator elevator) {
		viewLogger.trace("{} : Level {}\n", elevator.getName(), elevator.getCurrentFloor());
	}

	/**
	 * This method is used to display the message when picking up the user.
	 * 
	 * @param elevator : Elevator
	 */
	public static void pickingUser(Elevator elevator) {
		userLogger.info("{} : Picking user at level {}\n", elevator.getName(), elevator.getCurrentFloor());
	}

	/**
	 * This method is used to display the message when picking dropping off the
	 * user.
	 * 
	 * @param elevator : Elevator
	 */
	public static void droppingUser(Elevator elevator) {
		userLogger.info("{} : Dropping off user at level {}\n", elevator.getName(), elevator.getCurrentFloor());
	}

	/**
	 * This method is used to display the message when an elevator is starting a
	 * request.
	 * 
	 * @param elevator : Elevator
	 */
	public static void startElevator(Elevator elevator) {
		userLogger.info("{} : On the way from level {}\n", elevator.getName(), elevator.getCurrentFloor());
	}

	/**
	 * This method is used to display the message when an even/odd elevator is
	 * unable to reach the specified level and drops off user at the level below his
	 * request
	 * 
	 * @param elevator : Elevator
	 */
	public static void userHasToWalk(Elevator elevator) {
		userLogger.info("{} : Please take the stairs to level {}\n", elevator.getName(), elevator.getCurrentFloor() + 1);

	}
}
