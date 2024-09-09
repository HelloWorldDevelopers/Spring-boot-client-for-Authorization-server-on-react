package ai.rnt.customerFeedback.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class EmployeeProfileTest {

	@Test
	void testCustomerSatisfactionMasterEntity() {
		EmployeeProfile employeeProfile = new EmployeeProfile();

		employeeProfile.setProfileId(1);
		employeeProfile.setProfilePicture("qwdxwdx");

		assertEquals(1, employeeProfile.getProfileId());
		assertNotNull(employeeProfile.getProfileId());

		assertEquals("qwdxwdx", employeeProfile.getProfilePicture());
		assertNotNull(employeeProfile.getProfilePicture());

	}

}
