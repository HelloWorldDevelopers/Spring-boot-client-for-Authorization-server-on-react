package ai.rnt.customerFeedback.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CustomerSatisfactionMailBoxDTOTest {

	@Test
	void test() {
		CustomerSatisfactionMasterDto customerfeedback = new CustomerSatisfactionMasterDto();
		customerfeedback.setCustSatisfactionId(1);
		customerfeedback.setFilled(true);
		customerfeedback.setAddressId(1);
		CustomerSatisfactionMailBoxDTO customerFeedbackMailBoxDTO = new CustomerSatisfactionMailBoxDTO();
		customerFeedbackMailBoxDTO.setMailboxId(1);
		customerFeedbackMailBoxDTO.setCustomerSatisfactionMaster(customerfeedback);
		customerFeedbackMailBoxDTO.setEmailBody("email boddy");
		customerFeedbackMailBoxDTO.setToEmail("n.raut@rnt.si");
		customerFeedbackMailBoxDTO.setSubject("email body");
		customerFeedbackMailBoxDTO.setFromEmail("untrntapps@rabbitandtortortiose");
		assertEquals(1, customerFeedbackMailBoxDTO.getMailboxId());
		assertEquals("email boddy", customerFeedbackMailBoxDTO.getEmailBody());
		assertEquals("n.raut@rnt.si", customerFeedbackMailBoxDTO.getToEmail());
		assertEquals("email body", customerFeedbackMailBoxDTO.getSubject());
		assertEquals("untrntapps@rabbitandtortortiose", customerFeedbackMailBoxDTO.getFromEmail());
		assertEquals(customerfeedback, customerFeedbackMailBoxDTO.getCustomerSatisfactionMaster());

	}

}
