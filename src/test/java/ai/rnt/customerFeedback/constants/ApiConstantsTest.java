package ai.rnt.customerFeedback.constants;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ApiConstantsTest {

	@Test
     void testConstants() {
        assertEquals("/", ApiConstants.SEPERATOR);
        assertEquals("/api/v1/", ApiConstants.BASE);
        assertEquals("/api/v1/auth/", ApiConstants.AUTH);
        assertEquals("/login", ApiConstants.LOGIN);
        assertEquals("/tokenparse", ApiConstants.TOKENPARSE);
        assertEquals("/getAdminAndUser", ApiConstants.GET_ADMIN_AND_USER);
        assertEquals("/{id}", ApiConstants.ID);
        assertEquals("question", ApiConstants.QUESTION);
        assertEquals("customerFeedbackMaster", ApiConstants.CUSTOMER_FEEDBACK_MASTER);
        assertEquals("customerFeedback", ApiConstants.CUSTOMER_FEEDBACK);
        assertEquals("customerSatisfactionMaster", ApiConstants.CUSTOMER_SATISFACTION_MASTER);
        assertEquals("customerSatisfactionSurvey", ApiConstants.CUSTOMER_SATISFACTION_SURVEY);
        assertEquals("kycvendorform", ApiConstants.KYCVENDOR_FORM);
        assertEquals("/getAllCustomer", ApiConstants.ALL_CUSTOMER);
        assertEquals("/project", ApiConstants.PROJECT);
    }
}
