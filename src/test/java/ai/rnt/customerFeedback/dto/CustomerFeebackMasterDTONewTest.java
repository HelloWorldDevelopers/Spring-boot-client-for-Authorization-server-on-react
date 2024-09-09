package ai.rnt.customerFeedback.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CustomerFeebackMasterDTONewTest {

	@Test
	void testCustomerFeedbackDto() {
		Integer expectedCustomerFeedbackId = 1;
		String expectedAnswer = "Excellent";

		CustomerFeedbackMasterDTONew customerFeedbackMasterDto = new CustomerFeedbackMasterDTONew();

		customerFeedbackMasterDto.setCustFeedbackId(expectedCustomerFeedbackId);
		customerFeedbackMasterDto.setRole("eole");
		customerFeedbackMasterDto.setFeedbackBy(expectedAnswer);
		customerFeedbackMasterDto.setFilled(true);
		customerFeedbackMasterDto.setFilledDate("12-09-2024");
		customerFeedbackMasterDto.setEmailId("emailid");
		CustomerDto customerDTO = new CustomerDto();
		customerDTO.setCompanyName("wdci wcn");
		customerDTO.setCustomerId(1);
		customerDTO.setContactPersonName("widhc");
		customerDTO.setEmailId("gmail.com");
		ProjectDTOnew projectDTOnew = new ProjectDTOnew();

		projectDTOnew.setProjectId(1);
		projectDTOnew.setProjectName("project name");
		projectDTOnew.setCustomer(customerDTO);
		customerFeedbackMasterDto.setProject(projectDTOnew);

		assertEquals(expectedCustomerFeedbackId, customerFeedbackMasterDto.getCustFeedbackId());
		assertEquals("eole", customerFeedbackMasterDto.getRole());
		assertEquals(projectDTOnew, customerFeedbackMasterDto.getProject());
		assertEquals("emailid", customerFeedbackMasterDto.getEmailId());
		assertEquals("Excellent", customerFeedbackMasterDto.getFeedbackBy());
		assertEquals("12-09-2024", customerFeedbackMasterDto.getFilledDate());
		assertEquals(true, customerFeedbackMasterDto.getFilled());

	}

}
