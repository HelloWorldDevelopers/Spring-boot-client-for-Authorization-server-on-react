package ai.rnt.customerFeedback.payloads;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class ApiErrorTest {

	@Test
	void testApiErrorCreation() {
		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
		assertNotNull(apiError);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, apiError.getHttpStatus());
		assertNotNull(apiError.getTimestamp());
	}

	@Test
	void testApiErrorWithMessageAndErrors() {
		List<String> errors = Arrays.asList("Error 1", "Error 2");
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Bad request", errors);
		assertNotNull(apiError);
		assertEquals(HttpStatus.BAD_REQUEST, apiError.getHttpStatus());
		assertEquals("Bad request", apiError.getMessage());
		assertEquals(errors, apiError.getErrors());
		assertNotNull(apiError.getTimestamp());
	}

	@Test
	void testConstructorWithHttpStatusAndThrowable() {
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		Throwable ex = new RuntimeException("Test exception");

		ApiError apiError = new ApiError(httpStatus, ex);

		assertNotNull(apiError);
		assertNotNull(apiError.getTimestamp());
		assertEquals("Unexpected error", apiError.getMessage());
	}

	@Test
	void testConstructorWithMessageAndThrowable() {
		String message = "Test message";
		Throwable ex = new RuntimeException("Test exception");

		ApiError apiError = new ApiError(message, ex);
		apiError.setStatus(true);
		apiError.setTimestamp(null);
		apiError.setMessages(new ArrayList<>());
		apiError.setMessage("test");
		apiError.setErrors(new ArrayList<>());
		apiError.getMessage();
		assertNotNull(apiError);

	}

	@Test
	void testConstructorWithHttpStatusMessageAndThrowable() {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		String message = "Test message";
		Throwable ex = new RuntimeException("Test exception");

		ApiError apiError = new ApiError(httpStatus, message, ex);
		boolean status = false;

		new ApiError(status, message);
		String messageq = "Test error message";
		List<String> messages = Arrays.asList("Message 1", "Message 2");

		new ApiError(messageq, messages);
		assertNotNull(apiError);
		assertNotNull(apiError.getTimestamp());
		assertEquals(message, apiError.getMessage());

	}

	@Test
	void testConstructorWithStatusMessageErrorsAndHttpStatus() {
		boolean status = false;
		String message = "Test message";
		List<String> errors = Arrays.asList("Error 1", "Error 2");
		HttpStatus httpStatus = HttpStatus.NOT_FOUND;

		ApiError apiError = new ApiError(status, message, errors, httpStatus);

		assertNotNull(apiError);
		assertNotNull(apiError.getTimestamp());
		assertEquals(status, apiError.isStatus());
		assertEquals(message, apiError.getMessage());
		assertEquals(errors, apiError.getErrors());
		assertEquals(httpStatus, apiError.getHttpStatus());
	}

}
