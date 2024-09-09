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

import ai.rnt.customerFeedback.dto.CustomerFeedbackDto;
import ai.rnt.customerFeedback.enums.ApiResponse;
import ai.rnt.customerFeedback.service.CustomerFeedbackService;
@ExtendWith(MockitoExtension.class)
class CustomerFeedbackControllerTest2 {
	

		@Mock
		private CustomerFeedbackService customerFeedbackService;

		@InjectMocks
		private CustomerFeedbackController customerFeedbackController;

		@BeforeEach
		public void setUp() {
			MockitoAnnotations.openMocks(this);
		}

		@Test
		void testSaveCustomerFeedback() {
			List<CustomerFeedbackDto> customerFeedbackDtoList = createSampleCustomerFeedbackDtoList();
			ResponseEntity<EnumMap<ApiResponse, Object>> expectedResult = createSampleResponseEntity();

			when(customerFeedbackService.saveCustomerFeedback(customerFeedbackDtoList)).thenReturn(expectedResult);

			ResponseEntity<EnumMap<ApiResponse, Object>> response = customerFeedbackController
					.saveCustomerFeedback(customerFeedbackDtoList);

			assertEquals(expectedResult, response);
		}

		@Test
		void testGetAllCustomerFeedback() {
			ResponseEntity<EnumMap<ApiResponse, Object>> expectedResult = createSampleResponseEntity();

			when(customerFeedbackService.getAllCustomerFeedback()).thenReturn(expectedResult);

			ResponseEntity<EnumMap<ApiResponse, Object>> response = customerFeedbackController.getAllCustomerFeedback();

			assertEquals(expectedResult, response);
		}

		@Test
		void testGetByCustomerFeedback() {
			int customerId = 1;
			ResponseEntity<EnumMap<ApiResponse, Object>> expectedResult = createSampleResponseEntity();

			when(customerFeedbackService.getByCustomerFeedback(customerId)).thenReturn(expectedResult);

			ResponseEntity<EnumMap<ApiResponse, Object>> response = customerFeedbackController
					.getByCustomerFeedback(customerId);

			assertEquals(expectedResult, response);
		}

		private List<CustomerFeedbackDto> createSampleCustomerFeedbackDtoList() {
			List<CustomerFeedbackDto> customerFeedbackDtoList = new ArrayList<>();
			return customerFeedbackDtoList;
		}

		private ResponseEntity<EnumMap<ApiResponse, Object>> createSampleResponseEntity() {
			EnumMap<ApiResponse, Object> responseMap = new EnumMap<>(ApiResponse.class);
			responseMap.put(ApiResponse.SUCCESS, true);
			responseMap.put(ApiResponse.DATA, "Sample Data");
			return new ResponseEntity<>(responseMap, HttpStatus.OK);
		}
	
}
