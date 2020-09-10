package com.benjamin.elevator_project.model_test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import com.benjamin.elevator_project.elevator.Elevator;
import com.benjamin.elevator_project.elevator.IElevator;
import com.benjamin.elevator_project.model.Model;
import com.benjamin.elevator_project.properties.PropertiesSettings;
import com.benjamin.elevator_project.request.Request;

class ModelTest {
	
	Model model;
	
	@Test
	void test_Model_ReturnsElevatorListWithSizeIntTwenty_WhenIntTwentyIsPassedInToModelConstructor() {
		model = new Model(20);
		assertEquals(20, model.getElevators().size());
	}
	
	@Test
	void test_Model_ReturnsEwlevatorListWithSizeIntZero_WhenNegativeNumberIsPassedInToModelConstructor() {
		model = new Model(-2);
		assertEquals(0, model.getElevators().size());
	}
	
	@Test
	void test_ClosestElevator_ReturnsClosestElevatorTestOneAtLevelFive_WhenCalledWithRequestForLevelFour() {
		model = new Model(0);
		Elevator test1 = new Elevator("test1");
		List<IElevator> elevators = Arrays.asList(test1, new Elevator("test2"), new Elevator("test3"));  
		model.setElevators(elevators);
		PropertiesSettings.setProperties("config.properties");
		
		Request request = new Request("4:1", ":");
		test1.moveElevator(test1, 5);
		
		assertEquals(test1, model.closestElevator(request));
	}
	
	@Test
	void test_ClosestElevator_ReturnsClosestElevatorTestOneAtLevelTen_WhenCalledWithRequestForLevelSix() {
		model = new Model(0);
		Elevator test1 = new Elevator("test1");
		List<IElevator> elevators = Arrays.asList(test1, new Elevator("test2"), new Elevator("test3"));  
		model.setElevators(elevators);
		PropertiesSettings.setProperties("config.properties");
		
		Request request = new Request("6:10", ":");
		test1.moveElevator(test1, 10);
		
		assertEquals(test1, model.closestElevator(request));
	}
	
	

}
