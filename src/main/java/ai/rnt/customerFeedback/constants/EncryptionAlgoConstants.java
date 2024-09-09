package ai.rnt.customerFeedback.constants;

import org.springframework.beans.factory.annotation.Value;

public class EncryptionAlgoConstants {

	private EncryptionAlgoConstants() {
	}

	@Value("${rsa.rsakey}")
    public static final String RSA = "RSA";
	public static final String SHA1 = "SHA-1";
}
