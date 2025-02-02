package ai.rnt.customerFeedback.util;

import static java.util.Objects.isNull;

import javax.servlet.http.HttpServletRequest;

import org.springframework.lang.Nullable;

public class HttpUtils {

	private HttpUtils() {
	}

	/**
	 * This method will take HttpServletRequest and generate complete request URL.
	 * 
	 * @param request
	 * @return Complete URL of request.
	 * @since version 1.0
	 */
	@Nullable
	public static String getURL(HttpServletRequest request) {
		if (isNull(request))
			return null;
		StringBuilder url = new StringBuilder(request.getRequestURL().toString());
		String queryString = request.getQueryString();
		return queryString == null ? url.toString() : url.append('?').append(queryString).toString();
	}
}
