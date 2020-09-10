package com.benjamin.elevator_project.controller;

import com.benjamin.elevator_project.elevator.Elevator;
import com.benjamin.elevator_project.elevator.IElevator;
import com.benjamin.elevator_project.model.Model;
import com.benjamin.elevator_project.properties.PropertiesSettings;
import com.benjamin.elevator_project.request.Request;
import com.benjamin.elevator_project.view.View;

/**
 * @author Team 3
 * @version 1.0.0
 * @since 2020-09-09
 *
 */
public class Controller {
	private Model model;
	private View view;
	private Request request;
	public static boolean userWalkFlag = false;

	private static final String PROPERTIESFILE = "src/main/resources/config.properties";

	/**
	 * This constructor initialises the view and model objects. It also reads the
	 * config.properties file into PropertiesSettings class.
	 * 
	 */
	public Controller() {
		PropertiesSettings.setProperties(PROPERTIESFILE);
		view = new View();
		model = new Model(PropertiesSettings.getNumberOfElevators());
	}

	/** 
	 * This constructor initialises the view and model objects. It also reads the
	 * config.properties file into PropertiesSettings class.
	 * 
	 * @param model : Model
	 * @param view  : View
	 */
	public Controller(Model model, View view) {
		PropertiesSettings.setProperties(PROPERTIESFILE);
		this.view = view;
		this.model = model;
	}

	/**
	 * This method carries out the request into the view class and calls the
	 * processRequest method.
	 */
	public void serveRequest() {
		view.displayWelcome();
		processRequest(PropertiesSettings.getNextRequest());
	}

	/**
	 * This method processes multiple requests based on the delimiter and assigns
	 * each request to its specified elevator.
	 * 
	 * @param nextRequestDelimiter : String
	 */
	public void processRequest(String nextRequestDelimiter) {
		boolean flag;

		while (true) {
			String userInput = view.getInput();
			String[] indivRequest = userInput.split(nextRequestDelimiter);
			Elevator elevator;

			for (String req : indivRequest) {
				request = new Request(req, PropertiesSettings.getSrcDest());
				flag = request.processString();
				if (flag) {
					request.setFlag(false);
					break;
				}
				elevator = (Elevator) getElevatorMethod(request, PropertiesSettings.getMetric());
				synchronized (elevator) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					elevator.notifyAll();

				}
			}
		}

	}

	/**
	 * This method returns the designated elevator based on the metric specified by
	 * user.
	 * 
	 * @param request : Request
	 * @param metric  : int
	 * @return
	 */
	public IElevator getElevatorMethod(Request request, int metric) {
		Elevator elevator = null;
		switch (metric) {
		case 1:
			elevator = (Elevator) model.closestElevator(request);
			break;

		case 2:
			int src = request.getSrc();
			int dest = request.getDest();
			boolean flag;

			if ((src == 1 || src % 2 == 0) && dest % 2 == 0)
				elevator = (Elevator) model.evenElevator();

			else if (src % 2 != 0 && dest % 2 != 0)
				elevator = (Elevator) model.oddElevator();

			// odd to even OR even to odd
			else {
				request = model.getNewRequest(request);
				userWalkFlag = true;
				flag = request.processString();
				if (flag) {
					request.setFlag(false);
					break;
				}
				if (src % 2 == 0 && dest % 2 != 0)
					elevator = (Elevator) model.evenElevator();
				else
					elevator = (Elevator) model.oddElevator();
			}
			break;

		default:
			elevator = (Elevator) model.closestElevator(request);
			break;
		}
		elevator.setRequest(request);
		return elevator;
	}
}