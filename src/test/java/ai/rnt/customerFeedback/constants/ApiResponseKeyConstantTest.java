package ai.rnt.customerFeedback.constants;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ApiResponseKeyConstantTest {

	 @Test
	     void testConstants() {
	        assertEquals("MESSAGE", ApiResponseKeyConstant.MESSAGE);
	        assertEquals("DATA", ApiResponseKeyConstant.DATA);
	        assertEquals("SUCCESS", ApiResponseKeyConstant.SUCCESS);
	        assertEquals("TOKEN", ApiResponseKeyConstant.TOKEN);
	    }
}
