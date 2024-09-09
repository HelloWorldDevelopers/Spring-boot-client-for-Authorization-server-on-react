package ai.rnt.customerFeedback.payloads;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;

class JwtAuthRequestTest {

	private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@Test
	void testValidJwtAuthRequest() {
		JwtAuthRequest jwtAuthRequest = new JwtAuthRequest();
		jwtAuthRequest.setUserId("exampleUser");
		jwtAuthRequest.setPassword("examplePassword");

		Set<ConstraintViolation<JwtAuthRequest>> violations = validator.validate(jwtAuthRequest);

		assertTrue(violations.isEmpty());
	}

	@Test
	void testInvalidJwtAuthRequest_NullUserId() {
		JwtAuthRequest jwtAuthRequest = new JwtAuthRequest();
		jwtAuthRequest.setPassword("examplePassword");

		Set<ConstraintViolation<JwtAuthRequest>> violations = validator.validate(jwtAuthRequest);

	}

	@Test
	void testInvalidJwtAuthRequest_NullPassword() {
		JwtAuthRequest jwtAuthRequest = new JwtAuthRequest();
		jwtAuthRequest.setUserId("exampleUser");

		Set<ConstraintViolation<JwtAuthRequest>> violations = validator.validate(jwtAuthRequest);
	}

	@Test
	void testInvalidJwtAuthRequest_EmptyUserId() {
		JwtAuthRequest jwtAuthRequest = new JwtAuthRequest();
		jwtAuthRequest.setUserId("");
		jwtAuthRequest.setPassword("examplePassword");

		Set<ConstraintViolation<JwtAuthRequest>> violations = validator.validate(jwtAuthRequest);

		assertThat(violations).hasSize(1);
		assertThat(violations.iterator().next().getMessage()).isEqualTo("UserId should not be empty !!");
	}

	@Test
	void testInvalidJwtAuthRequest_EmptyPassword() {
		JwtAuthRequest jwtAuthRequest = new JwtAuthRequest();
		jwtAuthRequest.setUserId("exampleUser");
		jwtAuthRequest.setPassword("");

		Set<ConstraintViolation<JwtAuthRequest>> violations = validator.validate(jwtAuthRequest);

		assertThat(violations).hasSize(1);
		assertThat(violations.iterator().next().getMessage()).isEqualTo("Password should not be empty !!");
	}

	@Test
	void testValidJwtAuthRequest2() {
		JwtAuthRequest jwtAuthRequest = new JwtAuthRequest();
		jwtAuthRequest.setUserId("exampleUser");
		jwtAuthRequest.setPassword("examplePassword");

		Set<ConstraintViolation<JwtAuthRequest>> violations = validator.validate(jwtAuthRequest);

		assertTrue(violations.isEmpty());
		assertThat(jwtAuthRequest.getUserId()).isEqualTo("exampleUser");
		assertThat(jwtAuthRequest.getPassword()).isEqualTo("examplePassword");
	}

}
