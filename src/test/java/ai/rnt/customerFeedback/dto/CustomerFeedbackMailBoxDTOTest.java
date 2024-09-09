package ai.rnt.customerFeedback.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CustomerFeedbackMailBoxDTOTest {

	@Test
	void test() {
		CustomerFeedbackMasterDto customerfeedback = new CustomerFeedbackMasterDto();
		customerfeedback.setCustFeedbackId(1);
		customerfeedback.setEmailId("email.com");
		CustomerFeedbackMailBoxDTO customerFeedbackMailBoxDTO = new CustomerFeedbackMailBoxDTO();
		customerFeedbackMailBoxDTO.setMailboxId(1);
		customerFeedbackMailBoxDTO.setCustomerFeedbackMaster(customerfeedback);
		customerFeedbackMailBoxDTO.setEmailBody("email boddy");
		customerFeedbackMailBoxDTO.setToEmail("n.raut@rnt.si");
		customerFeedbackMailBoxDTO.setSubject("email body");
		customerFeedbackMailBoxDTO.setFromEmail("untrntapps@rabbitandtortortiose");
        assertEquals(1, customerFeedbackMailBoxDTO.getMailboxId());
        assertEquals("email boddy", customerFeedbackMailBoxDTO.getEmailBody());
        assertEquals("n.raut@rnt.si", customerFeedbackMailBoxDTO.getToEmail());
        assertEquals("email body", customerFeedbackMailBoxDTO.getSubject());
        assertEquals("untrntapps@rabbitandtortortiose", customerFeedbackMailBoxDTO.getFromEmail());
        assertEquals(customerfeedback, customerFeedbackMailBoxDTO.getCustomerFeedbackMaster());
    }

}
