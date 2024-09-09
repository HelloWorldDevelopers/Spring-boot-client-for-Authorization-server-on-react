package ai.rnt.customerFeedback.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EmployeePerformenceMasterNewDTOTest {

	@Test
	void test() {
		EmployeePerformenceMasterNewDTO employeePerformence = new EmployeePerformenceMasterNewDTO();
		employeePerformence.setEmpPerformenceId(1);
		employeePerformence.setFilled(true);
		employeePerformence.setProjectTeamId(1);

		assertEquals(1, employeePerformence.getEmpPerformenceId());
		assertEquals(1, employeePerformence.getProjectTeamId());
		assertEquals(true, employeePerformence.getFilled());

	}

}
