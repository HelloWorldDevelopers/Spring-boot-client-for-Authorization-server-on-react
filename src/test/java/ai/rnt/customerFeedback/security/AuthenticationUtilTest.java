package ai.rnt.customerFeedback.security;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

public class AuthenticationUtilTest {

	@InjectMocks
	AuthenticationUtil authenticationUtil;

	@Test
	public void testPublicApis() {
		List<String> expectedApis = List.of("/api/v1/auth/login", "/api/v1/auth/tokenparse",
				"/api/v1/question/getAllQuestion", "/api/v1/customerFeedbackMaster/{id}",
				"/api/v1/customerSatisfactionMaster/{id}", "/api/v1/employeePerformenceMaster/{id}",
				"/api/v1/kycvendorform/{id}", "/api/v1/kycvendorform", "/api/v1/upload", "/api/v1/employeeFeedback",
				"/api/v1/customerFeedback", "/api/v1/customerSatisfactionSurvey", "/api/v1/vendorupdate",
				"/api/v1/kycvendorfullform/{id}", "/api/v1/profilePic/{id}", "/api/v1/customerFeedback/{id}",
				"/api/v1/question", "/api/v1/generatePdf/{id}", "/api/v1/getAllStateWithCode", "/v3/api-docs",
				"swagger-resources/**", "swagger-ui/**", "/webjars/**");

		List<String> actualApis = authenticationUtil.PUBLIC_APIS.get();

	}

}
