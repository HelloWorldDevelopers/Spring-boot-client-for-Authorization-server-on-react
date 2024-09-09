package ai.rnt.customerFeedback.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ai.rnt.customerFeedback.dto.CustomerSatisfactionSurveyDto;
import ai.rnt.customerFeedback.enums.ApiResponse;
import ai.rnt.customerFeedback.service.CustomerSatisfactionSurveyService;
@ExtendWith(MockitoExtension.class)
class CustomerSatisfactionSurveyControllerTest {

	@Mock
	private CustomerSatisfactionSurveyService customerSatisfactionSurveyService;

	@InjectMocks
	private CustomerSatisfactionSurveyController customerSatisfactionSurveyController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	 void testSaveCustomerSatisfactionSurvey() {
		List<CustomerSatisfactionSurveyDto> list=new ArrayList<>();
		ResponseEntity<EnumMap<ApiResponse, Object>> expectedResult = createSampleResponseEntity();

		when(customerSatisfactionSurveyService.saveCustomerSatisfactionSurvey(list))
				.thenReturn(expectedResult);

		ResponseEntity<EnumMap<ApiResponse, Object>> response = customerSatisfactionSurveyController
				.saveCustomerSatisfactionSurvey(list);

		assertEquals(expectedResult, response);
	}

	@Test
	 void testGetCustomerSatisfactionSurveyById() {
		Integer id = 1;
		ResponseEntity<EnumMap<ApiResponse, Object>> expectedResult = createSampleResponseEntity();

		when(customerSatisfactionSurveyService.getCustomerSatisfactionSurveyById(id)).thenReturn(expectedResult);

		ResponseEntity<EnumMap<ApiResponse, Object>> response = customerSatisfactionSurveyController
				.getCustomerSatisfactionSurveyById(id);

		assertEquals(expectedResult, response);
	}

	@Test
	 void testGetAllCustomerSatisfactionSurvey() {
		ResponseEntity<EnumMap<ApiResponse, Object>> expectedResult = createSampleResponseEntity();

		when(customerSatisfactionSurveyService.getAllCustomerSatisfactionSurvey()).thenReturn(expectedResult);

		ResponseEntity<EnumMap<ApiResponse, Object>> response = customerSatisfactionSurveyController
				.getAllCustomerSatisfactionSurvey();

		assertEquals(expectedResult, response);
	}

	private CustomerSatisfactionSurveyDto createSampleCustomerSatisfactionSurveyDto() {
		return new CustomerSatisfactionSurveyDto();
	}

	private ResponseEntity<EnumMap<ApiResponse, Object>> createSampleResponseEntity() {
		EnumMap<ApiResponse, Object> responseMap = new EnumMap<>(ApiResponse.class);
		responseMap.put(ApiResponse.SUCCESS, true);
		responseMap.put(ApiResponse.DATA, "Sample Data");
		return new ResponseEntity<>(responseMap, HttpStatus.OK);
	}

}
