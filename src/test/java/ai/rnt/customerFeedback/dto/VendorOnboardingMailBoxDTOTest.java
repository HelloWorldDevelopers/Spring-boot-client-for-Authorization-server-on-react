package ai.rnt.customerFeedback.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class VendorOnboardingMailBoxDTOTest {

	@Test
	void test() {
		VendorKYCNewDTO vendorKYC = new VendorKYCNewDTO();
		vendorKYC.setVendorKycId(1);
		VendorOnboardingMailBoxDTO customerFeedbackMailBoxDTO = new VendorOnboardingMailBoxDTO();
		customerFeedbackMailBoxDTO.setMailboxId(1);
		customerFeedbackMailBoxDTO.setVendorKYC(vendorKYC);
		customerFeedbackMailBoxDTO.setEmailBody("email boddy");
		customerFeedbackMailBoxDTO.setToEmail("n.raut@rnt.si");
		customerFeedbackMailBoxDTO.setSubject("email body");
		customerFeedbackMailBoxDTO.setFromEmail("untrntapps@rabbitandtortortiose");
		assertEquals(1, customerFeedbackMailBoxDTO.getMailboxId());
		assertEquals("email boddy", customerFeedbackMailBoxDTO.getEmailBody());
		assertEquals("n.raut@rnt.si", customerFeedbackMailBoxDTO.getToEmail());
		assertEquals("email body", customerFeedbackMailBoxDTO.getSubject());
		assertEquals("untrntapps@rabbitandtortortiose", customerFeedbackMailBoxDTO.getFromEmail());
		assertEquals(vendorKYC, customerFeedbackMailBoxDTO.getVendorKYC());

	}

}
