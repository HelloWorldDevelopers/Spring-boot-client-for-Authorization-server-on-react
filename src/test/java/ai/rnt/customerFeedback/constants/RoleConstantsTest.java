package ai.rnt.customerFeedback.constants;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RoleConstantsTest {

	@Test
     void testRoleConstantsValues() {
        assertEquals("CustomerFeedBackAdmin", RoleConstants.CRM_ADMIN);
        assertEquals("CustomerFeedBackUser", RoleConstants.CRM_USER);
		/*
		 * assertEquals("hasAuthority('CustomerFeedBackAdmin') || hasAuthority('CustomerFeedBackUser')"
		 * , RoleConstants.CHECK_BOTH_ACCESS);
		 * assertEquals("hasAuthority('CustomerFeedBackAdmin')",
		 * RoleConstants.CHECK_ADMIN_ACCESS);
		 * assertEquals("hasAuthority('CustomerFeedBackUser')",
		 * RoleConstants.CHECK_USER_ACCESS);
		 */
        assertEquals("Don't Have Role", RoleConstants.NO_ROLE);
    }

}
