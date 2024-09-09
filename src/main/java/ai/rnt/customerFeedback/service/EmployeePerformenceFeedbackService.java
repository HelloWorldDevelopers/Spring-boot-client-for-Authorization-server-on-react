package ai.rnt.customerFeedback.service;

import java.util.EnumMap;
import java.util.List;

import org.springframework.http.ResponseEntity;

import ai.rnt.customerFeedback.dto.EmployeePerformenceFeedbackDTO;
import ai.rnt.customerFeedback.enums.ApiResponse;

public interface EmployeePerformenceFeedbackService {

	ResponseEntity<EnumMap<ApiResponse, Object>> getAllEmployeePerformenceFeedback();

	ResponseEntity<EnumMap<ApiResponse, Object>> getEmployeePerformenceFeedbackById(Integer id);

	ResponseEntity<EnumMap<ApiResponse, Object>> saveEmployeePerformenceFeedback(
			List<EmployeePerformenceFeedbackDTO> employeePerformenceFeedbackDTO);

}
