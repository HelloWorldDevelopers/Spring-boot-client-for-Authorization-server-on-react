package ai.rnt.customerFeedback.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CustomerListDtoTest {

	@Test
	void testCustomerListDto() {
		Integer expectedCustFeedbackId = 1;
		Integer expectedprojectId = 1;
		Integer expectedcustomerId = 1;

		String expectedRole = "Manager";
		String expectedFeedbackBy = "John Doe";
		String expectedCreatedDate = "2024-03-05";
		String expectedProjectName = "Sample Project";
		String expectedCompanyName = "ABC Company";
		Boolean expectedFilled = true;

		CustomerListDto customerListDto = new CustomerListDto();
		customerListDto.setCustFeedbackId(expectedCustFeedbackId);
		customerListDto.setRole(expectedRole);
		customerListDto.setFeedbackBy(expectedFeedbackBy);
		customerListDto.setCreatedDate(expectedCreatedDate);
		customerListDto.setProjectName(expectedProjectName);
		customerListDto.setProjectAlias("ABC");
		customerListDto.setCompanyName(expectedCompanyName);
		customerListDto.setFilled(expectedFilled);
		customerListDto.setProjectId(expectedprojectId);
		customerListDto.setCustomerId(expectedcustomerId);
		customerListDto.setEmailId("email.com");
		customerListDto.setFilledDate("22-03-2024");

		assertEquals(expectedCustFeedbackId, customerListDto.getCustFeedbackId());
		assertEquals(expectedRole, customerListDto.getRole());
		assertEquals(expectedFeedbackBy, customerListDto.getFeedbackBy());
		assertEquals(expectedCreatedDate, customerListDto.getCreatedDate());
		assertEquals(expectedProjectName, customerListDto.getProjectName());
		assertEquals(expectedCompanyName, customerListDto.getCompanyName());
		assertEquals(expectedFilled, customerListDto.getFilled());
		assertEquals(expectedprojectId, customerListDto.getProjectId());
		assertEquals(expectedcustomerId, customerListDto.getCustomerId());
		assertEquals("email.com", customerListDto.getEmailId());
		assertEquals("22-03-2024", customerListDto.getFilledDate());
		assertEquals("ABC", customerListDto.getProjectAlias());

	}
}