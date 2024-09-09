package ai.rnt.customerFeedback.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class RoleMasterTest {

	@Test
	void testRoleMasterEntity() {
		RoleMaster roleMaster = new RoleMaster();

		roleMaster.setRoleId(1);
		roleMaster.setRoleName("SampleRoleName");

		EmployeeMaster employee1 = new EmployeeMaster();
		EmployeeMaster employee2 = new EmployeeMaster();

		List<EmployeeMaster> employees = new ArrayList<>();
		employees.add(employee1);
		employees.add(employee2);

		roleMaster.setEmployees(employees);

		assertEquals(1, roleMaster.getRoleId());
		assertEquals("SampleRoleName", roleMaster.getRoleName());
		assertNotNull(roleMaster.getEmployees());

		roleMaster.setCreatedBy(1);
		roleMaster.setCreatedDate(LocalDateTime.now());
		roleMaster.setUpdatedBy(2);
		roleMaster.setUpdatedDate(LocalDateTime.now());
		roleMaster.setDeletedBy(3);
		roleMaster.setDeletedDate(LocalDateTime.now());

		assertEquals(1, roleMaster.getCreatedBy());
		assertNotNull(roleMaster.getCreatedDate());
		assertEquals(2, roleMaster.getUpdatedBy());
		assertNotNull(roleMaster.getUpdatedDate());
		assertEquals(3, roleMaster.getDeletedBy());
		assertNotNull(roleMaster.getDeletedDate());
	}
}
