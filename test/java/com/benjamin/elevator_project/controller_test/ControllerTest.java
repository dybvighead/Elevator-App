package com.benjamin.elevator_project.controller_test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.benjamin.elevator_project.controller.Controller;
import com.benjamin.elevator_project.elevator.IElevator;
import com.benjamin.elevator_project.model.Model;
import com.benjamin.elevator_project.request.Request;
import com.benjamin.elevator_project.view.View;

class ControllerTest {

	Controller controller;
	Request request;

	@Mock
	Model modelMock;

	@Mock
	View viewMock;

	@Mock
	IElevator elevatorMock;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void test_GetElevatorMethodReturnClosestElevatorFromClosestElevatorMethodInModel_WhenCalled() {

		// Arrange
		controller = new Controller(modelMock, viewMock);
		request = new Request("1:2", ",");

		when(modelMock.closestElevator(request)).thenReturn(elevatorMock);

		// Act
		controller.getElevatorMethod(request, 1);

		// Assert
		assertEquals(elevatorMock, controller.getElevatorMethod(request, 1));
	}

}
