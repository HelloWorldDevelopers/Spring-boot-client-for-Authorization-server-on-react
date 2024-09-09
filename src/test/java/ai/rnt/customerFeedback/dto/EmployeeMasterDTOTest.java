package ai.rnt.customerFeedback.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EmployeeMasterDTOTest {

	@Test
	void testEmployeeMasterDTO() {
		EmployeeMasterDTO employeeDTO = new EmployeeMasterDTO();
		employeeDTO.setStaffId(1);
		employeeDTO.setFirstName("John");
		employeeDTO.setLastName("Doe");
		employeeDTO.setEmailId("john.doe@example.com");
		employeeDTO.setUserId("johndoe123");
		employeeDTO.setFullName("John Doe");
		assertEquals(1, employeeDTO.getStaffId());
		assertEquals("John", employeeDTO.getFirstName());
		assertEquals("Doe", employeeDTO.getLastName());
		assertEquals("John Doe", employeeDTO.getFullName()); // fullName should be updated automatically
		assertEquals("john.doe@example.com", employeeDTO.getEmailId());
		assertEquals("johndoe123", employeeDTO.getUserId());
	}

}
