/**
 * @author Akash Bhor
 */
package ai.rnt.customerFeedback.constants;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import ai.rnt.customerFeedback.exception.CRMException;
import ai.rnt.customerFeedback.payloads.ApiError;

public class CRMExceptionTest {

    @Test
    void testConstructorWithExceptionString() {
        // Arrange
        String exceptionMessage = "Test exception message";

        // Act
        CRMException exception = new CRMException(exceptionMessage);

        // Assert
        assertNull(exception.getError());
        assertNull(exception.getException());
        assertEquals(exceptionMessage, exception.getMessage());
    }

    @Test
    void testConstructorWithExceptionAndApiError() {
        // Arrange
        String exceptionMessage = "Test exception message";
        ApiError apiError = new ApiError("Test error", new ArrayList<>());

        // Act
        CRMException exception = new CRMException(exceptionMessage, apiError);

        // Assert
        assertEquals(apiError, exception.getError());
        assertNull(exception.getException());
        assertEquals(exceptionMessage, exception.getMessage());
    }

    @Test
    void testConstructorWithException() {
        // Arrange
        Exception innerException = new Exception("Test inner exception");

        // Act
        CRMException exception = new CRMException(innerException);

        // Assert
        assertNull(exception.getError());
        assertEquals(innerException, exception.getException());
    }

    @Test
    void testConstructorWithApiErrorAndException() {
        // Arrange
        Exception innerException = new Exception("Test inner exception");
        ApiError apiError = new ApiError("Test error", new ArrayList<>());

        // Act
        CRMException exception = new CRMException(apiError, innerException);

        // Assert
        assertEquals(apiError, exception.getError());
    }

    // Test cases for getters and setters can also be added
}

