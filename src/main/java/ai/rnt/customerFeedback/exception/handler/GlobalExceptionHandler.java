package ai.rnt.customerFeedback.exception.handler;

import static org.apache.commons.lang3.exception.ExceptionUtils.getRootCause;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import java.security.InvalidKeyException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import ai.rnt.customerFeedback.exception.CRMException;
import ai.rnt.customerFeedback.payloads.ApiError;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(java.security.SignatureException.class)
	public ResponseEntity<ApiError> handleSignatureException(java.security.SignatureException ex) {
		return new ResponseEntity<>(new ApiError(false, "JWT Signature Is Not Valid!!", UNAUTHORIZED), UNAUTHORIZED);
	}

	 

	 

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ApiError> handleAccessDeniedException(AccessDeniedException ex) {
		return new ResponseEntity<>(new ApiError(false, "Your Not Authorized Person!!", UNAUTHORIZED), UNAUTHORIZED);
	}

	@ExceptionHandler(CRMException.class)
	protected ResponseEntity<ApiError> handleCRMException(CRMException exc) throws Throwable {
		log.info("handling CRM Exception....{}", exc.getLocalizedMessage());
		if (exc.getException() instanceof BadCredentialsException)
			return new ResponseEntity<>(new ApiError(false, "Bad Credentils", BAD_REQUEST), BAD_REQUEST);
		else if (exc.getException() instanceof InvalidKeyException)
			return new ResponseEntity<>(new ApiError(false, "Token Expired", UNAUTHORIZED), UNAUTHORIZED);
		 
		else if (exc.getException() instanceof NullPointerException)
			return new ResponseEntity<>(new ApiError(false, "Error Occured", INTERNAL_SERVER_ERROR),
					INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(new ApiError(false, getRootCause(exc).getMessage(), INTERNAL_SERVER_ERROR),
				INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> handleException(Exception ex) {
		return new ResponseEntity<>(new ApiError(false, getRootCause(ex).getMessage(), INTERNAL_SERVER_ERROR),
				INTERNAL_SERVER_ERROR);
	}
}
