package ai.rnt.customerFeedback.util;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import ai.rnt.customerFeedback.security.JWTTokenHelper;
import ai.rnt.customerFeedback.security.UserDetail;

@ExtendWith(MockitoExtension.class)
class AuditAwareUtilTest {

	@Mock
	private JWTTokenHelper helper;
	@InjectMocks
	AuditAwareUtil auditAwareUtil;

	@Test
	void testGetLoggedInStaffId() {

		auditAwareUtil.getLoggedInStaffId();
	}

	@Test
	void testGetLoggedInUserRole() {
		SecurityContext securityContext = mock(SecurityContext.class);
		SecurityContextHolder.setContext(securityContext);

		String username = "john_doe";
		String password = "password123";
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		Integer staffId = 123;
		String emailId = "john.doe@example.com";
		Collection<? extends GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));

		UserDetail userDetail = new UserDetail(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities, staffId, emailId);

		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetail, null);

		auditAwareUtil.getLoggedInUserRole();
	}

	@Test
	void testGetLoggedInUserName() {
		SecurityContext securityContext = Mockito.mock(SecurityContext.class);
		Authentication authentication = Mockito.mock(Authentication.class);

		when(securityContext.getAuthentication()).thenReturn(authentication);
		SecurityContextHolder.setContext(securityContext);

		String loggedInUserName = auditAwareUtil.getLoggedInUserName();

	}

	@Test
	 void testIsAdmin() {
		boolean isAdmin = auditAwareUtil.isAdmin();

	}

	@Test
	void testIsUser() {
		boolean isUser = auditAwareUtil.isUser();
	}
}
