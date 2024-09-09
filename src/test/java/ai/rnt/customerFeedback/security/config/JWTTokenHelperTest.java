package ai.rnt.customerFeedback.security.config;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import ai.rnt.customerFeedback.dto.EmployeeDto;
import ai.rnt.customerFeedback.dto.RBAC;
import ai.rnt.customerFeedback.security.JWTTokenHelper;
import ai.rnt.customerFeedback.service.EmployeeService;
import ai.rnt.customerFeedback.service.impl.RoleServiceImpl;

@ExtendWith(MockitoExtension.class)
class JWTTokenHelperTest {

	@Mock
	private EmployeeService service;

	@Mock
	private RoleServiceImpl roleService;

	@Mock
	private UserDetails userDetails;

	@InjectMocks
	JWTTokenHelper helper;

	@BeforeEach
	public void setUp() {
		List<String> roles = Arrays.asList("ROLE_USER", "ROLE_ADMIN");

		List<SimpleGrantedAuthority> authorities = roles.stream().map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
		userDetails = new User("123", "hsskksas", authorities);
	}

	@Test
	void testGenerateToken() {
		EmployeeDto emp = new EmployeeDto();
		emp.setFirstName("John");
		emp.setLastName("Doe");
		emp.setStaffId(123);
		emp.setEmailID("john.doe@example.com");

		RBAC rbac = new RBAC();
		List<RBAC> rbacList = Collections.singletonList(rbac);

		when(service.getEmployeeByUserId("123")).thenReturn(Optional.of(emp));
		when(roleService.getRbacList(123)).thenReturn(rbacList);

		String token = helper.generateToken(userDetails, null);

	}
}
