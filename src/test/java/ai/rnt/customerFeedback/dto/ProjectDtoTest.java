package ai.rnt.customerFeedback.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ProjectDtoTest {

	@Test
	void testProjectDto() {
		Integer expectedProjectId = 1;
		String expectedProjectName = "Sample Project";
		CustomerDto customer = new CustomerDto();
		customer.setCustomerId(101);
		customer.setCompanyName("ABC Company");
		ProjectDto projectDto = new ProjectDto();
		projectDto.setProjectId(expectedProjectId);
		projectDto.setProjectName(expectedProjectName);
		projectDto.setCustomer(customer);
		assertEquals(expectedProjectId, projectDto.getProjectId());
		assertEquals(expectedProjectName, projectDto.getProjectName());
		assertEquals(customer, projectDto.getCustomer());

	}
}