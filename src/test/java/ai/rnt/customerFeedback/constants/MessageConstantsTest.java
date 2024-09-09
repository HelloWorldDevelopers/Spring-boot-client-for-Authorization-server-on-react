package ai.rnt.customerFeedback.constants;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MessageConstantsTest {

	@Test
     void testConstants() {
        assertEquals("Your Credentails Are Not Valid", MessageConstants.BAD_CREDENTIALS);
        assertEquals("You Don't Have The Access!!", MessageConstants.ACCESS_DENIED);
        assertEquals("JWT Token is Expired!!", MessageConstants.TOKEN_EXPIRED);
    }

}
