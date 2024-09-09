package ai.rnt.customerFeedback.security.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;
import org.springframework.core.env.Environment;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.DefaultCsrfToken;

class PerRequestCsrfTokenRepositoryTest {

	@Test
	void testGenerateToken() {
		Environment env = mock(Environment.class);
		when(env.getProperty("cfb.token")).thenReturn("dummyToken");

		PerRequestCsrfTokenRepository repository = new PerRequestCsrfTokenRepository(env);

		HttpServletRequest request = mock(HttpServletRequest.class);

		CsrfToken csrfToken = repository.generateToken(request);

		assertNotNull(csrfToken);
		assertEquals("X-CSRF-TOKEN", csrfToken.getHeaderName());
		assertEquals("_csrf", csrfToken.getParameterName());
		assertEquals("dummyToken", csrfToken.getToken());
	}

	@Test
	void testSaveToken() {
		PerRequestCsrfTokenRepository repository = new PerRequestCsrfTokenRepository(mock(Environment.class));

		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);

		CsrfToken token = new DefaultCsrfToken("headerName", "parameterName", "tokenValue");

		repository.saveToken(token, request, response);

		verifyNoInteractions(response);
	}

	@Test
	void testLoadToken() {
		PerRequestCsrfTokenRepository repository = new PerRequestCsrfTokenRepository(mock(Environment.class));

		HttpServletRequest request = mock(HttpServletRequest.class);

		CsrfToken token = repository.loadToken(request);

		assertNull(token);
	}

}
