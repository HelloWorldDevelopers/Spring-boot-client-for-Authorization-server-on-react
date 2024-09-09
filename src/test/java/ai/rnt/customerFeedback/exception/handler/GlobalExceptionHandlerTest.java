package ai.rnt.customerFeedback.exception.handler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import java.security.InvalidKeyException;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;

import ai.rnt.customerFeedback.exception.CRMException;
import ai.rnt.customerFeedback.payloads.ApiError;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;

public class GlobalExceptionHandlerTest {

	private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

	@Test
	void handleSignatureException_ShouldReturnUnauthorized() {
		SignatureException ex = new SignatureException("Signature error");

		ResponseEntity<ApiError> response = handler.handleSignatureException(ex);

		assertThat(response.getStatusCode()).isEqualTo(UNAUTHORIZED);
		assertThat(response.getBody().getMessage()).isEqualTo("JWT Signature Is Not Valid!!");
	}

	@Test
	void handleExpiredJwtException_ShouldReturnUnauthorized() {
		ExpiredJwtException ex = new ExpiredJwtException(null, null, "Token expired");

		ResponseEntity<ApiError> response = handler.handleSignatureException(ex);

		assertThat(response.getStatusCode()).isEqualTo(UNAUTHORIZED);
		assertThat(response.getBody().getMessage()).isEqualTo("JWT Token Expired");
	}

	@Test
	void handleMalformedJwtException_ShouldReturnForbidden() {
		MalformedJwtException ex = new MalformedJwtException("Malformed token");

		ResponseEntity<ApiError> response = handler.handleMalformedJwtException(ex);

		assertThat(response.getStatusCode()).isEqualTo(FORBIDDEN);
		assertThat(response.getBody().getMessage()).isEqualTo("JWT Token is Not Valid!!");
	}

	@Test
	void handleAccessDeniedException_ShouldReturnUnauthorized() {
		AccessDeniedException ex = new AccessDeniedException("Access denied");

		ResponseEntity<ApiError> response = handler.handleAccessDeniedException(ex);

		assertThat(response.getStatusCode()).isEqualTo(UNAUTHORIZED);
		assertThat(response.getBody().getMessage()).isEqualTo("Your Not Authorized Person!!");
	}

	@Test
	void handleCRMException_WithBadCredentialsException_ShouldReturnBadRequest() throws Throwable {
		CRMException crmException = new CRMException(new BadCredentialsException("Bad credentials"));

		ResponseEntity<ApiError> response = handler.handleCRMException(crmException);

		assertThat(response.getStatusCode()).isEqualTo(BAD_REQUEST);
		assertThat(response.getBody().getMessage()).isEqualTo("Bad Credentils");
	}

	@Test
	void handleCRMException_WithInvalidKeyException_ShouldReturnUnauthorized() throws Throwable {
		CRMException crmException = new CRMException(new InvalidKeyException("Invalid key"));

		ResponseEntity<ApiError> response = handler.handleCRMException(crmException);

		assertThat(response.getStatusCode()).isEqualTo(UNAUTHORIZED);
		assertThat(response.getBody().getMessage()).isEqualTo("Token Expired");
	}

	@Test
	void handleCRMException_WithExpiredJwtException_ShouldReturnUnauthorized() throws Throwable {
		CRMException crmException = new CRMException(new ExpiredJwtException(null, null, "Token expired"));

		ResponseEntity<ApiError> response = handler.handleCRMException(crmException);

		assertThat(response.getStatusCode()).isEqualTo(UNAUTHORIZED);
		assertThat(response.getBody().getMessage()).isEqualTo("JWT Token Expired");
	}

	@Test
	void handleCRMException_WithNullPointerException_ShouldReturnInternalServerError() throws Throwable {
		CRMException crmException = new CRMException(new NullPointerException("Null pointer"));

		ResponseEntity<ApiError> response = handler.handleCRMException(crmException);

		assertThat(response.getStatusCode()).isEqualTo(INTERNAL_SERVER_ERROR);
		assertThat(response.getBody().getMessage()).isEqualTo("Error Occured");
	}

	@Test
	void handleException_ShouldReturnInternalServerError() {
		Exception ex = new Exception("General exception");

		ResponseEntity<ApiError> response = handler.handleException(ex);

		assertThat(response.getStatusCode()).isEqualTo(INTERNAL_SERVER_ERROR);
		assertThat(response.getBody().getMessage()).isEqualTo("General exception");
	}
}
