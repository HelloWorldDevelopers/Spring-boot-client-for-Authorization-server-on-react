package ai.rnt.customerFeedback.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class EmployeeMasterTest {

	@Test
	void testEmployeeMasterEntity() {
		EmployeeMaster employeeMaster = new EmployeeMaster();

		employeeMaster.setStaffId(1);
		employeeMaster.setUserId("sampleUserId");
		employeeMaster.setPassword("samplePassword");
		employeeMaster.setFirstName("John");
		employeeMaster.setMiddleName("Doe");
		employeeMaster.setLastName("Smith");
		employeeMaster.setEmailId("john.smith@example.com");
		employeeMaster.setManagerId(2);
		employeeMaster.setEmployeeJobTitle("Manager");
		employeeMaster.setDepartureDate(LocalDate.of(2022, 12, 31));

		RoleMaster role1 = new RoleMaster();
		RoleMaster role2 = new RoleMaster();

		List<RoleMaster> roles = new ArrayList<>();
		roles.add(role1);
		roles.add(role2);
		ProjectTeam projectTeam1 = new ProjectTeam();
		ProjectTeam projectTeam2 = new ProjectTeam();
		List<ProjectTeam> projectTeamList = new ArrayList<>();
		projectTeamList.add(projectTeam1);
		projectTeamList.add(projectTeam2);

		employeeMaster.setEmployeeRole(roles);
		employeeMaster.setProjectTeamList(projectTeamList);
		assertEquals(1, employeeMaster.getStaffId());
		assertEquals("sampleUserId", employeeMaster.getUserId());
		assertEquals("samplePassword", employeeMaster.getPassword());
		assertEquals("John", employeeMaster.getFirstName());
		assertEquals("Doe", employeeMaster.getMiddleName());
		assertEquals("Smith", employeeMaster.getLastName());
		assertEquals("john.smith@example.com", employeeMaster.getEmailId());
		assertEquals(2, employeeMaster.getManagerId());
		assertEquals("Manager", employeeMaster.getEmployeeJobTitle());
		assertEquals(LocalDate.of(2022, 12, 31), employeeMaster.getDepartureDate());
		assertNotNull(employeeMaster.getEmployeeRole());
		assertNotNull(employeeMaster.getProjectTeamList());

		employeeMaster.setCreatedBy(1);
		employeeMaster.setCreatedDate(LocalDateTime.now());
		employeeMaster.setUpdatedBy(2);
		employeeMaster.setUpdatedDate(LocalDateTime.now());
		employeeMaster.setDeletedBy(3);
		employeeMaster.setDeletedDate(LocalDateTime.now());

		assertEquals(1, employeeMaster.getCreatedBy());
		assertNotNull(employeeMaster.getCreatedDate());
		assertEquals(2, employeeMaster.getUpdatedBy());
		assertNotNull(employeeMaster.getUpdatedDate());
		assertEquals(3, employeeMaster.getDeletedBy());
		assertNotNull(employeeMaster.getDeletedDate());
	}

	@Test
	void testEmployeeMasterConstructor() {
		Integer staffId = 1;
		String firstName = "John";
		String lastName = "Doe";
		LocalDate departureDate = LocalDate.of(2022, 12, 31);

		EmployeeMaster employeeMaster = new EmployeeMaster(staffId, firstName, lastName, departureDate);

		assertEquals(staffId, employeeMaster.getStaffId());
		assertEquals(firstName, employeeMaster.getFirstName());
		assertEquals(lastName, employeeMaster.getLastName());
		assertEquals(departureDate, employeeMaster.getDepartureDate());
	}

}
