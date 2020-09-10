package com.benjamin.elevator_project.model;

import com.benjamin.elevator_project.elevator.IElevator;
import com.benjamin.elevator_project.request.Request;

@FunctionalInterface
public interface IDistanceCalculator {
	
	public int calculateDistance(IElevator elevator, Request request);

}
