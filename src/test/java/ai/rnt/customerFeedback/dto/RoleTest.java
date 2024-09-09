package ai.rnt.customerFeedback.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RoleTest {

    @Test
     void testRole() {
        Integer expectedRoleId = 1;
        String expectedRoleName = "ADMIN";
        Role role = new Role();
        role.setRoleId(expectedRoleId);
        role.setRoleName(expectedRoleName);
        assertEquals(expectedRoleId, role.getRoleId());
        assertEquals(expectedRoleName, role.getRoleName());

    }
}
