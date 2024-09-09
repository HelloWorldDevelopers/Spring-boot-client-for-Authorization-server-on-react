package ai.rnt.customerFeedback.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.EnumMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ai.rnt.customerFeedback.enums.ApiResponse;
import ai.rnt.customerFeedback.service.QuestionService;

@ExtendWith(MockitoExtension.class)
class QuestionControllerTest {

	@Mock
	private QuestionService questionService;

	@InjectMocks
	private QuestionController questionController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetAllQuestion() {
		ResponseEntity<EnumMap<ApiResponse, Object>> expectedResult = createSampleResponseEntity();
		when(questionService.getAllQuestion()).thenReturn(expectedResult);
		ResponseEntity<EnumMap<ApiResponse, Object>> response = questionController.getAllQuestion();
		assertEquals(expectedResult, response);
	}

	@Test
	void testGetAllCustomer() {
		ResponseEntity<EnumMap<ApiResponse, Object>> expectedResult = createSampleResponseEntity();
		when(questionService.getAllCustomer()).thenReturn(expectedResult);
		ResponseEntity<EnumMap<ApiResponse, Object>> response = questionController.getAllCustomer();
		assertEquals(expectedResult, response);
	}

	@Test
	void testGetProjectListByCustomerId() {
		int customerId = 123;
		ResponseEntity<EnumMap<ApiResponse, Object>> expectedResult = createSampleResponseEntity();
		when(questionService.getProjectListByCustomerId(customerId)).thenReturn(expectedResult);
		ResponseEntity<EnumMap<ApiResponse, Object>> response = questionController
				.getProjectListByCustomerId(customerId);
		assertEquals(expectedResult, response);
	}

	private ResponseEntity<EnumMap<ApiResponse, Object>> createSampleResponseEntity() {
		EnumMap<ApiResponse, Object> responseMap = new EnumMap<>(ApiResponse.class);
		responseMap.put(ApiResponse.SUCCESS, true);
		responseMap.put(ApiResponse.DATA, "Sample Data");
		return new ResponseEntity<>(responseMap, HttpStatus.OK);
	}

	@Test
	void testGetAllQuestionForCustomerSatisfaction() {
		int formType = 1;
		EnumMap<ApiResponse, Object> mockResponse = new EnumMap<>(ApiResponse.class);
		when(questionService.getAllQuestionForCustomerSatisfaction(formType))
				.thenReturn(ResponseEntity.ok(mockResponse));
		ResponseEntity<EnumMap<ApiResponse, Object>> responseEntity = questionController
				.getAllQuestionForCustomerSatisfaction(formType);
		assertEquals(mockResponse, responseEntity.getBody());
	}

	@Test
	void testGetCustomerByCustomerId() {
		int customerId = 123;
		EnumMap<ApiResponse, Object> mockResponse = new EnumMap<>(ApiResponse.class);

		when(questionService.getAllCustomerContactPerson(customerId)).thenReturn(ResponseEntity.ok(mockResponse));

		ResponseEntity<EnumMap<ApiResponse, Object>> responseEntity = questionController
				.getCustomerByCustomerId(customerId);

		assertEquals(mockResponse, responseEntity.getBody());
	}

	@Test
	void testGetAllCustomerForCustomerSatisfaction() {
		ResponseEntity<EnumMap<ApiResponse, Object>> expectedResult = createSampleResponseEntity();
		when(questionService.getAllCustomersForSatisfaction()).thenReturn(expectedResult);
		ResponseEntity<EnumMap<ApiResponse, Object>> response = questionController
				.getAllCustomerForCustomerSatisfaction();
		assertEquals(expectedResult, response);
	}

}
