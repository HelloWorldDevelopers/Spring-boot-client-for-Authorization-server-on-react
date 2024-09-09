package ai.rnt.customerFeedback.constants;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SecurityConstantTest {

	 @Test
	  void testSecurityConstantValues() {
	        assertEquals("Bearer", SecurityConstant.TOKEN_PREFIX_BEARER);
	        assertEquals("token", SecurityConstant.TOKEN_KEY);
	        assertEquals("custFeedbackId", SecurityConstant.CUSTOMERFEEDBACKID);
	    }

}
