package ai.rnt.customerFeedback.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ai.rnt.customerFeedback.entity.EmployeeMaster;
import ai.rnt.customerFeedback.entity.Project;
import ai.rnt.customerFeedback.entity.RoleMaster;

class ProjectTeamDTOTest {

	@Test
	void testGetterAndSetter() {
		ProjectTeamDTO dto = new ProjectTeamDTO();
		dto.setProjectTeamId(1);
		Project project = new Project();
		dto.setProject(project);
		EmployeeMaster employeeMaster = new EmployeeMaster();
		dto.setEmployeeMastar(employeeMaster);
		RoleMaster roleMaster = new RoleMaster();
		dto.setRoleMaster(roleMaster);
		assertEquals(1, dto.getProjectTeamId());
		assertEquals(project, dto.getProject());
		assertEquals(employeeMaster, dto.getEmployeeMastar());
		assertEquals(roleMaster, dto.getRoleMaster());
	}

}
