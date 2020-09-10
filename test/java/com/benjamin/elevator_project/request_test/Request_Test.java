package com.benjamin.elevator_project.request_test;


import static org.junit.Assert.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.exceptions.misusing.MissingMethodInvocationException;

import com.benjamin.elevator_project.exceptions.FormatException;
import com.benjamin.elevator_project.exceptions.NegativeException;
import com.benjamin.elevator_project.request.Request;

import static org.mockito.Mockito.*;

class Request_Test {
	Request request;

	@Mock
	Request requestMock;

	@BeforeEach
	public void set_up() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void test_ReturnFormatExceptionWhenStringDoesNotMatchSrcDestWhenRequestIsCalled() {
		// Arrange
		String string = "13342";
		String delimiter = ":";

		// Act
//		Controller controller = new Controller();
		request = new Request(string, delimiter);
		request.processString();

//		controller.processRequest(" ");
//		requestMock = new Request(string, delimiter);

		// Assert
		assertThrows(FormatException.class, () -> {
			when(requestMock.processString()).thenThrow(new FormatException("Input should be in format src:dest"));
			request.processString();
		});
	}

	@Test
	void test_ReturnNumberFormatExceptionWhenStringContainsAlphabetsWhenRequestIsCalled() {
		// Arrange
		String string = "ab:cd";
		String delimiter = ":";

		// Act
		request = new Request(string, delimiter);

		// Assert
		assertThrows(MissingMethodInvocationException.class, () -> {
			when(request.processString()).thenThrow(new NumberFormatException("Input should be in format src:dest"));
			new Request(string, delimiter);
		});
	}

	@Test
	void test_ReturnNegativeExceptionWhenStringContainsNegativeNumbersWhenRequestIsCalled() {
		// Arrange
		String string = "-1:10";
		String delimiter = ":";

		// Act
		request = new Request(string, delimiter);

		// Assert
		assertThrows(MissingMethodInvocationException.class, () -> {
			when(request.processString()).thenThrow(new NegativeException("Input should be positive integer"));
			new Request(string, delimiter);
		});
	}

}
