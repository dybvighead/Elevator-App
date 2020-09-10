package com.benjamin.elevator_project.app;

import com.benjamin.elevator_project.controller.Controller;

/**
 * @author Team 3
 * @version 1.0.0
 * @since 2020-09-09
 *
 */
public class ElevatorApp {

	public static void main(String[] args) {
		Controller controller = new Controller();
		controller.serveRequest();
	}
}
