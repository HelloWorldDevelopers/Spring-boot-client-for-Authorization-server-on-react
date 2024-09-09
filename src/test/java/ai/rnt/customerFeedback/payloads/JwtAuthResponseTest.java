package ai.rnt.customerFeedback.payloads;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class JwtAuthResponseTest {

	@Test
	void testJwtAuthResponseBuilder() {
		boolean expectedStatus = true;
		String expectedToken = "exampleToken";

		JwtAuthResponse jwtAuthResponse = JwtAuthResponse.builder().status(expectedStatus).token(expectedToken).build();

		assertNotNull(jwtAuthResponse);
		assertEquals(expectedStatus, jwtAuthResponse.isStatus());
		assertEquals(expectedToken, jwtAuthResponse.getToken());
	}

	@Test
	void testGetterMethods() {
		boolean expectedStatus = true;
		String expectedToken = "exampleToken";
		JwtAuthResponse jwtAuthResponse = new JwtAuthResponse(expectedStatus, expectedToken);
		jwtAuthResponse.setStatus(expectedStatus);
		jwtAuthResponse.setToken(expectedToken);

		assertEquals(expectedStatus, jwtAuthResponse.isStatus());
		assertEquals(expectedToken, jwtAuthResponse.getToken());
	}

}
