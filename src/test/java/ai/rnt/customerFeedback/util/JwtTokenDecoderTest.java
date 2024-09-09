package ai.rnt.customerFeedback.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class JwtTokenDecoderTest {

	@Test
	void testDecodeJWT() {
		String sampleToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

		JwtTokenDecoder jwtTokenDecoder = new JwtTokenDecoder();

		String decodedPayload = jwtTokenDecoder.testDecodeJWT(sampleToken);

		assertEquals("{\"sub\":\"1234567890\",\"name\":\"John Doe\",\"iat\":1516239022}", decodedPayload);
	}

}
