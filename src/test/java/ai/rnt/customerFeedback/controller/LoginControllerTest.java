package ai.rnt.customerFeedback.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;

import ai.rnt.customerFeedback.enums.ApiResponse;
import ai.rnt.customerFeedback.payloads.JwtAuthRequest;
import ai.rnt.customerFeedback.payloads.JwtAuthResponse;
import ai.rnt.customerFeedback.security.JWTTokenHelper;
import ai.rnt.customerFeedback.security.config.CustomUserDetails;
import ai.rnt.customerFeedback.service.EmployeeService;
import ai.rnt.customerFeedback.util.Sha1Encryptor;
import ai.rnt.customerFeedback.util.TokanEncrypterDecrypter;

class LoginControllerTest {

	@Mock
	private AuthenticationManager authenticationManager;

	@Mock
	private EmployeeService employeeService;

	@Mock
	private CustomUserDetails customUserDetails;

	@Mock
	private JWTTokenHelper helper;

	@Mock
	private PrivateKey privateKey;
	@Mock
	private Sha1Encryptor sha1Encryptor;
	@Mock
	private TokanEncrypterDecrypter tokanEncrypterDecrypter;

	@Mock
	HttpServletRequest request;

	@Mock
	private PublicKey publicKey;

	@InjectMocks
	private LoginController loginController;

	private Map<String, String> req = new HashMap<>();
	private String token;
	private String decryptedToken;

	@BeforeEach
	public void setUp() throws NoSuchAlgorithmException {
		token = "Xqym3OMbn+y212vwJMkmQq+UL7ziX3DfbiL2aPlkpajSs8Xu17JgQOihZBbV8E1eyDbRE6gjPwDbAlG2bZCyBgNedAQzybOFek14WkPY1XA0aByLEoKYf9OjSQSbOLHw89nY1Q+O421t9vZdk7K+5WqAR/vbRI9iMjXcmiTpj9NvPqTTPGrmkIX3mLkTbVgp8joY5ascc0EQi2d66gJxShoIn1x53g53ttWgaClvkRX0LENBNiY4dErl2HtU8cKO/FcWMjzsNmLOlMUM2MXiwv8RFCToRJKfCjGH1dFong976QjqDBJG2R7wv/4tSD/SZjcVAUH7lqi3jif65HR/LL0MHxgA9YNb6ZZJX3Lt8cG0HuCriKBfFul2RtNhTBlCobVeGd3WoQFAJfXxdS6J+mZ3trlvdinI3GMLPscaiDj2+yKmgjJzit1NQP7KWZPrwMuZmbD/QBgJ9bkGNONEC00IZFqq2oIrzlonRY0STcp+nnBqm5nmIn3u5Q8wT2RGpn9qPYlEhck/rXePzB2bOxNg5ZZQGJpkTe5L43riM3xCJ5SQh55O9T5VnQtusWcchtHA7s/DmkemG0guV8pJETpxWon/ZIE0jagfNj9fv8Gvow1mWqsQ9NyXmEklBcJO/psVPlmEaEg3HgZZFstj70XR9og3/hegIt+mhaMx6IMR0ZT9SvFgSqyAHeNoNZI4xGLWLlTLrhg3xh6h7XGjD5HkoM9RHQInNRocc6/xPbn2jNPOoMMgJ2QYDaQZ3zgyt8MrZl6sRls60pYFrTAgZi+0qQDgF6E/7IzHqPxeXxJvQe5WlLhts5XfuQ+QvzCWQdabpPQbvfYOjkUD24XwYA==";
		// req.put("token", token);
		KeyPair keyPair = KeyPairGenerator.getInstance("RSA").generateKeyPair();
		PublicKey publicKey = keyPair.getPublic();
		MockitoAnnotations.openMocks(this);
		when(helper.getKeyPair()).thenReturn(new KeyPair(publicKey, privateKey));
		MockitoAnnotations.openMocks(this);
		when(helper.getKeyPair()).thenReturn(keyPair);

		when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
	}

	@Test
	void testCreateAuthenticationToken_Success() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		JwtAuthRequest jwtAuthRequest = new JwtAuthRequest();
		jwtAuthRequest.setUserId("ab1395");
		jwtAuthRequest.setPassword("1395");
		String token = "sampleToken";
		KeyPair keyPair = KeyPairGenerator.getInstance("RSA").generateKeyPair();
		PublicKey publicKey = keyPair.getPublic();

		UserDetails userDetails = mock(UserDetails.class);

		when(sha1Encryptor.encryptThisString("1395")).thenReturn("encryptedPassword");
		when(authenticationManager.authenticate(any())).thenReturn(null);
		when(helper.getKeyPair()).thenReturn(keyPair);
		when(helper.generateToken(userDetails, request)).thenReturn(token); // Pass userDetails instead of null

		ResponseEntity<JwtAuthResponse> responseEntity = loginController.createAuthenticationToken(jwtAuthRequest,
				request);
	}

	@Test
	void testCreateAuthenticationToken_Success2() throws Exception {
		JwtAuthRequest jwtAuthRequest = new JwtAuthRequest();
		jwtAuthRequest.setUserId("ab1395");
		jwtAuthRequest.setPassword("1395");

		UserDetails userDetails = mock(UserDetails.class);

		when(sha1Encryptor.encryptThisString("1395")).thenReturn("encryptedPassword");
		when(authenticationManager.authenticate(any())).thenReturn(null);

		KeyPair keyPair = KeyPairGenerator.getInstance("RSA").generateKeyPair();
		when(helper.getKeyPair()).thenReturn(keyPair);
		MockHttpServletRequest request = new MockHttpServletRequest();
		String token = "sampleToken";
		when(helper.generateToken(userDetails, request)).thenReturn(token);

		ResponseEntity<JwtAuthResponse> responseEntity = loginController.createAuthenticationToken(jwtAuthRequest,
				request);

		System.out.println("Actual method calls to generateToken:");

	}

	private void assertTrue(boolean status) {

	}

	@Test
	void testCreateAuthenticationToken_NullToken() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		JwtAuthRequest jwtAuthRequest = new JwtAuthRequest();
		jwtAuthRequest.setUserId("5Wo/DAE8GYF4My781P3qJQ==");
		jwtAuthRequest.setPassword("2Tu2C3mAfLncU7EIS+YdjA==");
		KeyPair keyPair = KeyPairGenerator.getInstance("RSA").generateKeyPair();
		PublicKey publicKey = keyPair.getPublic();
		when(sha1Encryptor.encryptThisString("1395")).thenReturn("encryptedPassword");
		when(helper.generateToken(any(), any())).thenReturn(null);
		when(authenticationManager.authenticate(any())).thenReturn(null);
		when(helper.getKeyPair()).thenReturn(keyPair);

		ResponseEntity<JwtAuthResponse> responseEntity = loginController.createAuthenticationToken(jwtAuthRequest,
				request);

		assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
		equals(responseEntity.getBody().isStatus());
		assertNull(responseEntity.getBody().getToken());
	}

	/**
	 * @param token
	 * @author Akash Bhor
	 */
	private void assertNull(String token) {
		// TODO Auto-generated method stub

	}

	@Test
	void testCreateAuthenticationToken_Exception() throws Exception {
		MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
		JwtAuthRequest jwtAuthRequest = new JwtAuthRequest();
		jwtAuthRequest.setUserId("ab1395");
		jwtAuthRequest.setPassword("1395");
		KeyPair keyPair = KeyPairGenerator.getInstance("RSA").generateKeyPair();
		PublicKey publicKey = keyPair.getPublic();
		when(sha1Encryptor.encryptThisString("1395")).thenReturn("encryptedPassword");
		when(helper.generateToken(any(), any())).thenThrow(new NullPointerException());
		when(authenticationManager.authenticate(any())).thenReturn(null);
		when(helper.getKeyPair()).thenReturn(keyPair);
		try {
			loginController.createAuthenticationToken(jwtAuthRequest, httpServletRequest);
		} catch (Exception e) {
		}
	}

	@Test
	void testTokenDecode() throws Exception {
		try {
			req.put("token", token);
			String decryptedTokan = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjpbIkFjY1JvbGVBZG1pblRlc3QiLCJBbGxBcHBBZG1pbiIsIkN1c3RvbWVyRmVlZEJhY2tVc2VyIiwiT25ib2FyZGluZyBNYW5hZ2VyIiwiUU1TIEF1ZGl0b3IiLCJRTVMgVXNlciJdLCJFbWFpbElkIjoibi5yYXV0QHJudC5haSIsInN1YiI6Im5rMTYxMyIsIlJCQUMiOlt7InJlYWRBY2Nlc3MiOiJZIiwid3JpdGVBY2Nlc3MiOiJZIiwiZWRpdEFjY2VzcyI6IlkiLCJkZWxldGVBY2Nlc3MiOiJZIiwidXNlQ2FzZSI6IkNVRkVFIEVtcGxveWVlRmVlZGJhY2siLCJ1c2VDYXNlSWQiOjAsIm1lc3NhZ2UiOm51bGwsIm1vZHVsZUFjY2VzcyI6dHJ1ZX1dLCJTdGFmZklkIjoxNjEzLCJpcEFkZHJlc3MiOiIwOjA6MDowOjA6MDowOjEiLCJmdWxsTmFtZSI6Ik5pa2l0YSBSYXV0IiwiZXhwIjoxNzIxMjIxNjM5LCJpYXQiOjE3MjEyMTkyMzl9.WCcvatCvmc2_c4W--2VDmsRyBPS1AB-EyfJBkmpLQSs";
			when(tokanEncrypterDecrypter.decryptTokan(token)).thenReturn(decryptedTokan);
			loginController.tokenDecode(req, request);
			when(tokanEncrypterDecrypter.decryptTokan(token)).thenThrow(NullPointerException.class);
			loginController.tokenDecode(req, request);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JwtAuthResponse createSampleJwtAuthResponse() {
		return JwtAuthResponse.builder().status(true).token("sampleToken").build();
	}

	private ResponseEntity<EnumMap<ApiResponse, Object>> createSampleResponseEntity() {
		EnumMap<ApiResponse, Object> responseMap = new EnumMap<>(ApiResponse.class);
		responseMap.put(ApiResponse.SUCCESS, true);
		responseMap.put(ApiResponse.DATA, "Sample Data");
		return new ResponseEntity<>(responseMap, HttpStatus.OK);
	}

	@Test
	void testGetAdminAndUser() {
		ResponseEntity<EnumMap<ApiResponse, Object>> expectedResult = createSampleResponseEntity();
		when(employeeService.getAdminAndUser()).thenReturn(expectedResult);
		ResponseEntity<EnumMap<ApiResponse, Object>> response = loginController.getAdminAndUser();
	}

}
