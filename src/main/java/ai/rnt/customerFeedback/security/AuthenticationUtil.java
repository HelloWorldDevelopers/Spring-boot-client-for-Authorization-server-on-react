package ai.rnt.customerFeedback.security;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class AuthenticationUtil {

	public static final Predicate<String> ALLOW_URL = s -> AuthenticationUtil.PUBLIC_APIS.get().stream()
			.anyMatch(s::startsWith);

	public static final Supplier<List<String>> PUBLIC_APIS = () -> {
		List<String> api = new ArrayList<>();
		api.add("/api/v1/auth/login");
//		api.add("/api/v1/auth/tokenparse");
		api.add("/api/v1/question/getAllQuestion");
		api.add("/api/v1/customerFeedbackMaster/{id}");
		api.add("/api/v1/customerSatisfactionMaster/{id}");
		api.add("/api/v1/employeePerformenceMaster/{id}");
		api.add("/api/v1/kycvendorform/{id}");
		api.add("/api/v1/kycvendorform");
		api.add("/api/v1/upload");
		api.add("/api/v1/employeeFeedback");
		api.add("/api/v1/customerFeedback");
		api.add("/api/v1/customerSatisfactionSurvey");
		api.add("/api/v1/vendorupdate");
		api.add("/api/v1/kycvendorfullform/{id}");
		api.add("/api/v1/profilePic/{id}");
		api.add("/api/v1/customerFeedback/{id}");
		api.add("/api/v1/question");
		api.add("/api/v1/generatePdf/{id}");
		api.add("/api/v1/getAllStateWithCode");
		api.add("/v3/api-docs");
		api.add("swagger-resources/**");
		api.add("swagger-ui/**");
		api.add("/webjars/**");
		return api;

	};

	protected static final String[] PUBLIC_GET_URLS = { "/api/v1/customerSatisfactionMaster/{id}",
			"/api/v1/customerSatisfactionSurvey/{id}",

			"/api/v1/employeePerformenceMaster/{id}", "/api/v1/employeeFeedback/{id}",

			"/api/v1/question/{formType}",

			"/api/v1/kycvendorform/{id}",

			"/api/v1/getstate/{code}",

			"/api/v1/getAllCountry",

			"/api/v1/getAllStateWithCode",

			"/api/v1/generatePdf/{id}", "/api/v1/getrntlogo/{imangeName}",

			"/api/v1/customerFeedbackMaster/{id}", "/api/v1/customerFeedback/{id}", "/api/v1/getPdfFile/{id}/{docId}" };

	protected static final String[] PUBLIC_POST_URLS = { "/api/v1/customerFeedback", "/api/v1/employeeFeedback",
			"/api/v1/customerSatisfactionSurvey", "/api/v1/upload" };

	protected static final String[] PUBLIC_PUT_URLS = { "/api/v1/kycvendorform", "/api/v1/vendorupdate",
			"/api/v1/customerSatisfactionMaster/{id}", "/api/v1/employeePerformenceMaster/{id}",
			"/api/v1/customerFeedbackMaster/{id}"

	};

	protected static final String[] PUBLIC_URLS = { "/api/v1/auth/login", "/api/v1/question/**", "/v3/api-docs",
			"/v2/api-docs", "swagger-resources/**", "swagger-ui/**", "/webjars/**", };
	public static final String[] ADMIN_URLS = { "/api/v1/admin/**" };
	public static final String[] USER_URLS = { "/api/v1/user/**" };

}
