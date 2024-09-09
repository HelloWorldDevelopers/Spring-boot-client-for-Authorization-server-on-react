package ai.rnt.customerFeedback.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

class UserDetailTest {

	@Test
	void testConstructorAndGetters() {
		String username = "testUser";
		String password = "password";
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		Collection<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
		Integer staffId = 123;
		String emailId = "test@example.com";

		UserDetail userDetails = new UserDetail(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities, staffId, emailId);

		assertEquals(username, userDetails.getUsername());
		assertEquals(password, userDetails.getPassword());
		assertTrue(userDetails.isEnabled());
		assertTrue(userDetails.isAccountNonExpired());
		assertTrue(userDetails.isCredentialsNonExpired());
		assertTrue(userDetails.isAccountNonLocked());
		assertEquals(authorities.size(), userDetails.getAuthorities().size());
		assertTrue(userDetails.getAuthorities().containsAll(authorities));
		assertEquals(staffId, userDetails.getStaffId());
		assertEquals(emailId, userDetails.getEmailId());
	}

	@Test
	void testSetters() {
		UserDetail userDetails = new UserDetail("username", "password", true, true, true, true,
				Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")), 123, "test@example.com");
	}

}