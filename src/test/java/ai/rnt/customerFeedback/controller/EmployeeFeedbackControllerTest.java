package ai.rnt.customerFeedback.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ai.rnt.customerFeedback.dto.EmployeePerformenceFeedbackDTO;
import ai.rnt.customerFeedback.enums.ApiResponse;
import ai.rnt.customerFeedback.service.EmployeePerformenceFeedbackService;

@ExtendWith(MockitoExtension.class)
class EmployeeFeedbackControllerTest {

	@Mock
	EmployeePerformenceFeedbackService employeePerformenceFeedbackService;

	@InjectMocks
	EmployeeFeedbackController employeeFeedbackController;

	@Test
	void testgetAllPerformenceFeedback() {
		EnumMap<ApiResponse, Object> mockResponse = new EnumMap<>(ApiResponse.class);
		when(employeePerformenceFeedbackService.getAllEmployeePerformenceFeedback())
				.thenReturn(ResponseEntity.ok(mockResponse));
		ResponseEntity<EnumMap<ApiResponse, Object>> responseEntity = employeeFeedbackController
				.getAllPerformenceFeedback();
		assertEquals(mockResponse, responseEntity.getBody());
	}

	@Test
	void testGetPerformenceFeedbackById() {
		EnumMap<ApiResponse, Object> mockResponse = new EnumMap<>(ApiResponse.class);
		when(employeePerformenceFeedbackService.getEmployeePerformenceFeedbackById(anyInt()))
				.thenReturn(ResponseEntity.ok(mockResponse));
		ResponseEntity<EnumMap<ApiResponse, Object>> responseEntity = employeeFeedbackController
				.getPerformenceFeedbackById(anyInt());
		assertEquals(mockResponse, responseEntity.getBody());

	}

	@Test
	void testSaveEmployeeFeedback() {
		List<EmployeePerformenceFeedbackDTO> employeePerformenceMasterDTO = new ArrayList<>();
		ResponseEntity<EnumMap<ApiResponse, Object>> expectedResult = createSampleResponseEntity();
		when(employeePerformenceFeedbackService.saveEmployeePerformenceFeedback(employeePerformenceMasterDTO))
				.thenReturn(expectedResult);
		ResponseEntity<EnumMap<ApiResponse, Object>> response = employeeFeedbackController
				.saveEmployeeFeedback(employeePerformenceMasterDTO);
		assertEquals(expectedResult, response);
	}

	private ResponseEntity<EnumMap<ApiResponse, Object>> createSampleResponseEntity() {
		EnumMap<ApiResponse, Object> responseMap = new EnumMap<>(ApiResponse.class);
		responseMap.put(ApiResponse.SUCCESS, true);
		responseMap.put(ApiResponse.DATA, "Sample Data");
		return new ResponseEntity<>(responseMap, HttpStatus.OK);
	}

}
