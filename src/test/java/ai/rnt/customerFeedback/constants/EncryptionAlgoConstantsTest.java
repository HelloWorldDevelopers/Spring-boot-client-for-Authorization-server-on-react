package ai.rnt.customerFeedback.constants;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EncryptionAlgoConstantsTest {

	@Test
     void testConstants() {
        assertEquals("RSA", EncryptionAlgoConstants.RSA);
        assertEquals("SHA-1", EncryptionAlgoConstants.SHA1);
    }

}
