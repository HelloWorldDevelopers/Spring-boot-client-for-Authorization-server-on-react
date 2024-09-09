package ai.rnt.customerFeedback.util;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RSAToJwtDecoderTest {

	@InjectMocks
	RSAToJwtDecoder rSAToJwtDecoder;

	@Test
	void testRsaToJwtDecoder_InvalidInput() {
		String invalidInputString = "invalidString";
		assertThrows(Exception.class, () -> rSAToJwtDecoder.rsaToJwtDecoder(invalidInputString));
	}

}
