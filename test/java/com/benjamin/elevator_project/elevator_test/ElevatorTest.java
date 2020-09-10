package com.benjamin.elevator_project.elevator_test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import com.benjamin.elevator_project.elevator.Elevator;
import com.benjamin.elevator_project.view.View;

class ElevatorTest {

	@Mock
	View viewMock;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void test_moveElevatorMethodMovesElevatorToTargetFloor_WhenCalled() {

		// Arrange
		Elevator elevator = new Elevator("Elevator 1", viewMock);

		// Act
		elevator.moveElevator(elevator, 10);

		// Assert
		assertEquals(10, elevator.getCurrentFloor());

	}

	@Test
	void test_moveElevatorMethodCallsDisplayFloorFromView_WhenCalled() {

		// Arrange
		Elevator elevator = new Elevator("Elevator 1", viewMock);

		// Act
		elevator.moveElevator(elevator, 10);

		// Assert
		verify(viewMock, times(1)).displayFloor(elevator);

	}


}
