package ai.rnt.customerFeedback.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ProjectDTOnewTest {

	@Test
	void testProjectDto() {
		Integer expectedProjectId = 1;
		String expectedProjectName = "Sample Project";
		CustomerDto customer = new CustomerDto();
		customer.setCustomerId(101);
		customer.setCompanyName("ABC Company");
		ProjectDTOnew projectDto = new ProjectDTOnew();
		projectDto.setProjectId(expectedProjectId);
		projectDto.setProjectName(expectedProjectName);
		projectDto.setProjectAlias("ABC");
		projectDto.setCustomer(customer);
		assertEquals(expectedProjectId, projectDto.getProjectId());
		assertEquals(expectedProjectName, projectDto.getProjectName());
		assertEquals("ABC", projectDto.getProjectAlias());
		assertEquals(customer, projectDto.getCustomer());

	}
}
