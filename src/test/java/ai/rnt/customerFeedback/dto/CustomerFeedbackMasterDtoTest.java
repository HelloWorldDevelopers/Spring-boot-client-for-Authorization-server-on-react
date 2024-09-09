package ai.rnt.customerFeedback.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CustomerFeedbackMasterDtoTest {

	@Test
	void testCustomerFeedbackMasterDto() {
		Integer expectedCustFeedbackId = 1;
		String expectedRole = "Manager";
		String expectedFeedbackBy = "John Doe";
		ProjectDto projectDto = new ProjectDto();
		projectDto.setProjectId(101);
		projectDto.setProjectName("Sample Project");
		CustomerFeedbackMasterDto customerFeedbackMasterDto = new CustomerFeedbackMasterDto();
		customerFeedbackMasterDto.setCustFeedbackId(expectedCustFeedbackId);
		customerFeedbackMasterDto.setRole(expectedRole);
		customerFeedbackMasterDto.setFeedbackBy(expectedFeedbackBy);
		customerFeedbackMasterDto.setProjectDto(projectDto);
		assertEquals(expectedCustFeedbackId, customerFeedbackMasterDto.getCustFeedbackId());
		assertEquals(expectedRole, customerFeedbackMasterDto.getRole());
		assertEquals(expectedFeedbackBy, customerFeedbackMasterDto.getFeedbackBy());
		assertEquals(projectDto, customerFeedbackMasterDto.getProjectDto());

	}
}
