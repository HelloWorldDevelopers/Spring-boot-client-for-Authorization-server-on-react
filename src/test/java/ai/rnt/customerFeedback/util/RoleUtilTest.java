package ai.rnt.customerFeedback.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import ai.rnt.customerFeedback.constants.RoleConstants;

@ExtendWith(MockitoExtension.class)
class RoleUtilTest {

	@Test
	void testAllowRoles_ValidRole() {
		assertTrue(RoleUtil.ALLOW_ROLES.test(RoleConstants.CRM_ADMIN));
		assertTrue(RoleUtil.ALLOW_ROLES.test(RoleConstants.CRM_USER));
	}

	@Test
	void testAllowRoles_InvalidRole() {
		assertFalse(RoleUtil.ALLOW_ROLES.test("InvalidRole"));
	}

	@Test
	void testGetRole_ValidRole() {
		assertEquals(RoleConstants.CRM_ADMIN, RoleUtil.GET_ROLE.apply(RoleConstants.CRM_ADMIN));
		assertEquals(RoleConstants.CRM_USER, RoleUtil.GET_ROLE.apply(RoleConstants.CRM_USER));
	}

	@Test
	void testGetRole_InvalidRole() {
		assertEquals(RoleConstants.NO_ROLE, RoleUtil.GET_ROLE.apply("InvalidRole"));
	}

	@Test
	void testCheckAdmin_ValidRole() {
		assertTrue(RoleUtil.CHECK_ADMIN.test(RoleConstants.CRM_ADMIN));
	}

	@Test
	void testCheckAdmin_InvalidRole() {
		assertFalse(RoleUtil.CHECK_ADMIN.test(RoleConstants.CRM_USER));
	}

	@Test
	void testCheckUser_ValidRole() {
		assertTrue(RoleUtil.CHECK_USER.test(RoleConstants.CRM_USER));
	}

	@Test
	void testCheckUser_InvalidRole() {
		assertFalse(RoleUtil.CHECK_USER.test(RoleConstants.CRM_ADMIN));
	}

	@Test
	void testGetSingleRole_CRMAdmin() {
		List<String> roles = Arrays.asList(RoleConstants.CRM_ADMIN, RoleConstants.CRM_USER);
		assertEquals(RoleConstants.CRM_ADMIN, RoleUtil.getSingleRole(roles));
	}

	@Test
	void testGetSingleRole_NoRole() {
		List<String> roles = Arrays.asList("OtherRole", "AnotherRole");
		assertEquals(RoleConstants.NO_ROLE, RoleUtil.getSingleRole(roles));
	}
}
