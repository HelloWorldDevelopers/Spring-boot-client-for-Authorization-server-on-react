package ai.rnt.customerFeedback.security.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import ai.rnt.customerFeedback.dto.EmployeeDto;
import ai.rnt.customerFeedback.dto.RBAC;
import ai.rnt.customerFeedback.security.UserDetail;
import ai.rnt.customerFeedback.service.EmployeeService;
import ai.rnt.customerFeedback.service.impl.RoleServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CustomUserDetailsTest {

	@Mock
	private EmployeeService employeeService;

	@Mock
	private RoleServiceImpl roleService;

	@InjectMocks
	private CustomUserDetails customUserDetails;

	@BeforeEach
	public void setUp() {
	}

	@Test
	public void testLoadUserByUsername_success() {
		EmployeeDto user = new EmployeeDto();
		user.setUserID("testUser");
		user.setPassword("password");
		user.setStaffId(123);
		user.setEmailID("john.doe@example.com");

		List<String> roles = Arrays.asList("ROLE_USER", "ROLE_ADMIN");
		RBAC rbac = new RBAC();
		rbac.setUseCase("CustomerProjectEndFeedback");
		List<RBAC> rbacList = Arrays.asList(rbac

		);

		when(employeeService.getEmployeeByUserId("testUser")).thenReturn(Optional.of(user));

		UserDetail userDetail = customUserDetails.loadUserByUsername("testUser");

		assertNotNull(userDetail);
		assertEquals("testUser", userDetail.getUsername());
		assertEquals("password", userDetail.getPassword());
		assertEquals(123, userDetail.getStaffId());
		assertEquals("john.doe@example.com", userDetail.getEmailId());

		List<String> expectedRoles = Arrays.asList("ROLE_USER", "ROLE_ADMIN", "CustomerProjectEndFeedback",
				"CustomerSatisfactionSurvey", "CustomerEmployeeFeedback", "CustomerVendorOnboarding");
		List<String> actualRoles = new ArrayList<>();
		for (GrantedAuthority authority : userDetail.getAuthorities()) {
			actualRoles.add(authority.getAuthority());
		}
	}

	@Test
    public void testLoadUserByUsername_employeeNotFound() {
        when(employeeService.getEmployeeByUserId("testUser")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> {
        	customUserDetails.loadUserByUsername("testUser");
        });
    }

	@Test
    public void testLoadUserByUsername_exceptionHandling() {
        when(employeeService.getEmployeeByUserId("testUser")).thenThrow(new RuntimeException());

        assertThrows(UsernameNotFoundException.class, () -> {
        	customUserDetails.loadUserByUsername("testUser");
        });
    }
}
