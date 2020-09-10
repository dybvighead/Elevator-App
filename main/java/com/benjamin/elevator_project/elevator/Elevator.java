package com.benjamin.elevator_project.elevator;

import com.benjamin.elevator_project.controller.Controller;
import com.benjamin.elevator_project.request.Request;
import com.benjamin.elevator_project.view.View;

/**
 * @author Team 3
 * @version 1.0.0
 * @since 2020-09-09
 *
 */
public class Elevator implements IElevator, Runnable {
	private Request request;

	private String name;
	private int currentFloor;
	private boolean available;
	private View view;
	private String type = null;

	/**
	 * This constructor initialises the name, current floor, and availability of the
	 * elevator.
	 * 
	 * @param name : String
	 * @param view : View
	 */
	public Elevator(String name, View view) {
		this.name = name;
		this.currentFloor = 1;
		this.available = true;
		this.view = view;
	}

	/**
	 * This constructor initialises the name, current floor, and availability of the
	 * elevator.
	 * 
	 * @param name : String
	 */
	public Elevator(String name) {
		this.name = name;
		this.currentFloor = 1;
		this.available = true;
	}

	/**
	 * This method moves the elevator from its current floor to the target floor.
	 * 
	 * @param elevator : IElevator
	 * @param target   : int
	 */
	public void moveElevator(IElevator elevator, int target) {

		while (currentFloor != target) {

			// go up
			if (((Elevator) elevator).getCurrentFloor() > target) {

				if ((((Elevator) elevator).getType() == null) || (((Elevator) elevator).getType().equals("even")
						&& ((Elevator) elevator).getCurrentFloor() == 2))
					currentFloor--;
				else
					currentFloor -= 2;

				// go down
			} else {
				if ((((Elevator) elevator).getType() == null) || (((Elevator) elevator).getType().equals("even")
						&& ((Elevator) elevator).getCurrentFloor() == 1))
					currentFloor++;
				else
					currentFloor += 2;
			}

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			View.displayFloor(this);
		}
	}

	/**
	 * This method returns the name of the elevator.
	 * 
	 * @return name : String
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method returns the current floor of the elevator.
	 * 
	 * @return currentFloor : int
	 */
	public int getCurrentFloor() {
		return currentFloor;
	}

	/**
	 * This method assigns the specified request to the elevator.
	 * 
	 * @return request : Request
	 */
	public void setRequest(Request request) {
		this.request = request;
	}

	/**
	 * This method returns the availability of the elevator.
	 * 
	 * @return available : boolean
	 */
	public boolean isAvailable() {
		return available;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * This method fulfils the request assigned to the elevator.
	 *
	 */
	@Override
	public void run() {
		while (true) {
			try {
				synchronized (this) {
					wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			available = false;

			View.startElevator(this);
			moveElevator(this, request.getSrc());
			View.pickingUser(this);
			moveElevator(this, request.getDest());
			View.droppingUser(this);
			if (Controller.userWalkFlag) {
				View.userHasToWalk(this);
				Controller.userWalkFlag = false;
			}

			available = true;

		}
	}

}
