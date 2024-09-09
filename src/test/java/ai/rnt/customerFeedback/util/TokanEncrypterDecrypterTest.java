package ai.rnt.customerFeedback.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TokanEncrypterDecrypterTest {
	@InjectMocks
	private TokanEncrypterDecrypter tokanEncrypterDecrypter;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testEncryptTokan() throws Exception {
		String inputToken = "exampleToken";
		tokanEncrypterDecrypter.setAlgorithm("AES");
		tokanEncrypterDecrypter.setAlgorithmSecretKey("ThisIsASecretKey");
		assertNotNull(tokanEncrypterDecrypter.encryptTokan(inputToken));
	}

	@Test
	void testdecryptTokan() throws Exception {
		String inputToken = "RdarzUSnHDraz/OEHdfIQSVpruwjFF3UfCbHPLxK8Gw7Ry3fLwvpW62d/bSM6n8xgiNMkEonxdC6k7HsrjAfIK+BCYaHfGQGGX5Kopi2pd863iL3RqfT3x7LUEgPTpB48rs7C0hK5Z9FCiTijT751gftSjo4T7vZIDx0+cO8cY405bmdZVil3S3z9dPLrv/L5WXiD64h0alzJYfRV564jx7Us+hbfHmyjfvia+E1WbgLyrdevdETYfGGz3OJkAviC1i8AodhA8OqvHMmumPhQ2hCXsROVD5hnJTGbse0vxfAIHu+ccU9xInhXzlfqsWORcjeUMCOB9omxRw243uGLvZfQMg9Uiiyg3r1hlaHtZyzJtOv2O3E1+FzMobpQYYjPm+8GpNmEA7KP3gZn5zP/VtTV7+NjOnlwcnxUg4QW7bmAfg2vq4U6rYwd60mfrhQ9FFgaORTvtv/u3Pe3AXFFw==";
		tokanEncrypterDecrypter.setAlgorithm("AES");
		tokanEncrypterDecrypter.setAlgorithmSecretKey("ThisIsASecretKey");
		assertNotNull(tokanEncrypterDecrypter.decryptTokan(inputToken));

	}

	@Test
	void testDecrypt() {
		String encryptedText = "ltRqgLT8cDnPzgnK/zUxkA==";
		tokanEncrypterDecrypter.setAlgorithmSecretKey("ThisIsASecretKey");
		;
		tokanEncrypterDecrypter.setCipherInstance("AES/CBC/PKCS5PADDING");
		String decryptedText = tokanEncrypterDecrypter.decrypt(encryptedText);
	}

	@Test
	void testDecryptWithInvalidInput() {
		String invalidEncryptedText = null;
		String decryptedText = tokanEncrypterDecrypter.decrypt(invalidEncryptedText);
		assertNull(decryptedText);
	}

}
