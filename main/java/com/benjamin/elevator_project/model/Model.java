package com.benjamin.elevator_project.model;

import java.util.ArrayList;
import java.util.List;

import com.benjamin.elevator_project.elevator.Elevator;
import com.benjamin.elevator_project.elevator.IElevator;
import com.benjamin.elevator_project.properties.PropertiesSettings;
import com.benjamin.elevator_project.request.Request;

/**
 * @author Team 3
 * @version 1.0.0
 * @since 2020-09-09
 *
 */
public class Model {
	private static List<IElevator> elevators;
	private IElevator closestElevator;
	private IElevator oddEvenElevator;

	/**
	 * This constructor initialises list of elevators objects, as well as their
	 * threads. It adds the elevators into the elevators list.
	 * 
	 * @param numberOfElevators : int
	 */
	public Model(int numberOfElevators) {
		elevators = new ArrayList<>();
		Elevator elevator;
		Thread elevatorThread;

		for (int i = 1; i <= numberOfElevators; i++) {
			elevator = new Elevator("Elevator " + i);

			if (PropertiesSettings.getMetric() == 2) {
				if (i % 2 == 0) {
					elevator.setType("even");
				} else
					elevator.setType("odd");
			}

			elevatorThread = new Thread(elevator);

			elevatorThread.start();
			elevators.add(elevator);
		}
	}

	/**
	 * This method identifies the closest elevator to the request and returns it.
	 * 
	 * @param request : Request
	 * @return closestElevator : IElevator
	 */
	public IElevator closestElevator(Request request) {
		int minDist = PropertiesSettings.getNumberOfFloors();
		IDistanceCalculator distanceCalculator;

		for (IElevator elevator : elevators) {

			if (((Elevator) elevator).isAvailable()) {
				distanceCalculator = (e, r) -> Math.abs(((Elevator) e).getCurrentFloor() - r.getSrc());
				int dist = distanceCalculator.calculateDistance(elevator, request);
				if (dist < minDist) {
					minDist = dist;
					closestElevator = elevator;
				}
			}

		}
		return closestElevator;
	}

	/**
	 * This method will return an even elevator.
	 * 
	 * @return oddEvenElevator : Elevator
	 */
	public IElevator evenElevator() {
		for (IElevator elevator : elevators) {
			if (((Elevator) elevator).isAvailable() && ((Elevator) elevator).getType() == "even") {
				oddEvenElevator = elevator;
				break;
			}
		}
		return oddEvenElevator;
	}
	
	/**
	 * This method will return an odd elevator.
	 * 
	 * @return oddEvenElevator : Elevator
	 */
	public IElevator oddElevator() {
		for (IElevator elevator : elevators) {
			if (((Elevator) elevator).isAvailable() && ((Elevator) elevator).getType() == "odd") {
				oddEvenElevator = elevator;
				break;
			}
		}
		return oddEvenElevator;
	}

	/**
	 * This method alters the user request to fit in the odd-even algorithm.
	 * 
	 * @param request : Request
	 * @return nRequest : Request
	 */
	public Request getNewRequest(Request request) {
		String newRequest = null;
		if (request.getDest() == 1)
			newRequest = Integer.toString(request.getSrc()) + ":" + Integer.toString(request.getDest());
		else
			newRequest = Integer.toString(request.getSrc()) + ":" + Integer.toString(request.getDest() - 1);
		Request nRequest = new Request(newRequest, PropertiesSettings.getSrcDest());
		return nRequest;

	}

	/**
	 * This method gets the list of elevators.
	 * 
	 * @return elevators : List<IElevator>
	 */
	public List<IElevator> getElevators() {
		return elevators;
	}

	/**
	 * This method sets the list of elevators.
	 * 
	 * @param elevators : List<IElevator>
	 */
	public void setElevators(List<IElevator> elevators) {
		this.elevators = elevators;
	}

	/**
	 * This method gets the specified elevator by it's index in the elevators list.
	 * 
	 * @param i : int
	 * @return elevator : Elevator
	 */
	public static Elevator getElevator(int i) {
		return (Elevator) elevators.get(i);
	}

}
