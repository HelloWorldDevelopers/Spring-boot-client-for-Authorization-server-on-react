package ai.rnt.customerFeedback.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;

@ExtendWith(MockitoExtension.class)
class HttpUtilsTest {

	@Test
	void testGetURL() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setScheme("http");
		request.setServerName("example.com");
		request.setServerPort(8080);
		request.setContextPath("/myapp");
		request.setRequestURI("/myapp/resource");
		request.setQueryString("param1=value1&param2=value2");

		String url = HttpUtils.getURL(request);

		assertEquals("http://example.com:8080/myapp/resource?param1=value1&param2=value2", url);
	}

	@Test
	void testGetURLWithNullRequest() {
		String url = HttpUtils.getURL(null);

		assertNull(url);
	}

}
