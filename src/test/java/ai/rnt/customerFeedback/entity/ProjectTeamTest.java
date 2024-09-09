package ai.rnt.customerFeedback.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProjectTeamTest {

	@Test
	void testProjectTeamEntity() {
		ProjectTeam projectTeam = new ProjectTeam();

		projectTeam.setProjectTeamId(1);

		Project project = new Project();
		projectTeam.setProject(project);

		EmployeeMaster employeeMaster = new EmployeeMaster();
		projectTeam.setEmployeeMastar(employeeMaster);

		RoleMaster roleMaster = new RoleMaster();
		projectTeam.setRoleMaster(roleMaster);

		assertEquals(1, projectTeam.getProjectTeamId());
		assertNotNull(projectTeam.getProject());
		assertNotNull(projectTeam.getEmployeeMastar());
		assertNotNull(projectTeam.getRoleMaster());

		projectTeam.setCreatedBy(1);
		projectTeam.setCreatedDate(LocalDateTime.now());
		projectTeam.setUpdatedBy(2);
		projectTeam.setUpdatedDate(LocalDateTime.now());
		projectTeam.setDeletedBy(3);
		projectTeam.setDeletedDate(LocalDateTime.now());

		assertEquals(1, projectTeam.getCreatedBy());
		assertNotNull(projectTeam.getCreatedDate());
		assertEquals(2, projectTeam.getUpdatedBy());
		assertNotNull(projectTeam.getUpdatedDate());
		assertEquals(3, projectTeam.getDeletedBy());
		assertNotNull(projectTeam.getDeletedDate());
	}

}
