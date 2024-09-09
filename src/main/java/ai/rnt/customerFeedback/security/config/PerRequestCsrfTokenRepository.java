package ai.rnt.customerFeedback.security.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PerRequestCsrfTokenRepository implements CsrfTokenRepository {
	private static final String DEFAULT_CSRF_HEADER_NAME = "X-CSRF-TOKEN";
	private static final String DEFAULT_CSRF_PARAMETER_NAME = "_csrf";

	private final Environment env;

	@Override
	public CsrfToken generateToken(HttpServletRequest request) {
		return new DefaultCsrfToken(DEFAULT_CSRF_HEADER_NAME, DEFAULT_CSRF_PARAMETER_NAME,
				env.getProperty("cfb.token"));
	}

	@Override
	public void saveToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response) {
	}

	@Override
	public CsrfToken loadToken(HttpServletRequest request) {
		return null;
	}

}
