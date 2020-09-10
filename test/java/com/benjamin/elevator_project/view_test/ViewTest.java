package com.benjamin.elevator_project.view_test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import com.benjamin.elevator_project.elevator.Elevator;
import com.benjamin.elevator_project.view.View;

class ViewTest {
	
	View view;
	
	@Mock
	Elevator elevatorMock;
	
	@BeforeEach
	void setup() {
		view = new View();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void test_GetInputMethodReturnsStringOneColonNineWhenStringOneColonNine_IsPassedInAsInput() {

		String userInput;
		String expected = "1:9";

		userInput = view.getInput();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		assertEquals(expected, userInput);
		
	}
	
	@Test
	void test_DisplayFloorMethodCallsGetNameMethodFromElevator_WhenCalled() {
		
		View.displayFloor(elevatorMock);
		verify(elevatorMock, times(1)).getName();
		
	}
	
	@Test
	void test_DisplayFloorMethodCallsGetCurrentFloorMethodFromElevator_WhenCalled() {
		
		View.displayFloor(elevatorMock);
		verify(elevatorMock, times(1)).getCurrentFloor();
		
	}
	
	@Test
	void test_PickingUserMethodCallsGetNameMethodFromElevator_WhenCalled() {
		
		View.pickingUser(elevatorMock);
		verify(elevatorMock, times(1)).getName();
		
	}
	
	@Test
	void test_PickingUserMethodCallsGetCurrentFloorMethodFromElevator_WhenCalled() {
		
		View.pickingUser(elevatorMock);
		verify(elevatorMock, times(1)).getCurrentFloor();
		
	}
	
	@Test
	void test_DroppingUserMethodCallsGetNameMethodFromElevator_WhenCalled() {
		
		View.droppingUser(elevatorMock);
		verify(elevatorMock, times(1)).getName();
		
	}
	
	@Test
	void test_DroppingUserMethodCallsGetCurrentFloorMethodFromElevator_WhenCalled() {
		
		View.droppingUser(elevatorMock);
		verify(elevatorMock, times(1)).getCurrentFloor();
		
	}

}
