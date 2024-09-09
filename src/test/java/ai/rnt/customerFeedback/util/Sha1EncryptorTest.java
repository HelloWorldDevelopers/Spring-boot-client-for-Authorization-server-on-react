package ai.rnt.customerFeedback.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class Sha1EncryptorTest {

	private Sha1Encryptor sha1Encryptor;

	@BeforeEach
	void setup() {
		sha1Encryptor = new Sha1Encryptor();
		sha1Encryptor.setSha1("SHA-1");
	}

	@Test
	void testEncryptThisString() {
		String input = "password";
		String expectedHash = "5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8";
		String actualHash = sha1Encryptor.encryptThisString(input);
		assertEquals(expectedHash, actualHash);
	}
}