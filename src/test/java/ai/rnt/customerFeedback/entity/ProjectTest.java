package ai.rnt.customerFeedback.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class ProjectTest {

	@Test
	void testProjectEntity() {
		Project project = new Project();

		project.setProjectId(1);
		project.setProjectName("SampleProjectName");
		project.setProjectStartDate(LocalDate.now());
		project.setProjectEndDate(LocalDate.now());
		Customer customer = new Customer();
		project.setCustomer(customer);

		assertEquals(1, project.getProjectId());
		assertEquals("SampleProjectName", project.getProjectName());
		assertNotNull(project.getCustomer());

		assertEquals(LocalDate.now(), project.getProjectStartDate());
		assertEquals(LocalDate.now(), project.getProjectEndDate());
		assertEquals("SampleProjectName", project.getProjectName());

		project.setCreatedBy(1);
		project.setCreatedDate(LocalDateTime.now());
		project.setUpdatedBy(2);
		project.setUpdatedDate(LocalDateTime.now());
		project.setDeletedBy(3);
		project.setDeletedDate(LocalDateTime.now());

		assertEquals(1, project.getCreatedBy());
		assertNotNull(project.getCreatedDate());
		assertEquals(2, project.getUpdatedBy());
		assertNotNull(project.getUpdatedDate());
		assertEquals(3, project.getDeletedBy());
		assertNotNull(project.getDeletedDate());
	}

}
