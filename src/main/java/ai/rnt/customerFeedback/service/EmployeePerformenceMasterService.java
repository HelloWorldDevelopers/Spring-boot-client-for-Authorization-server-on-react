package ai.rnt.customerFeedback.service;

import java.util.EnumMap;

import org.springframework.http.ResponseEntity;

import ai.rnt.customerFeedback.dto.EmployeePerformenceMailBoxDTO;
import ai.rnt.customerFeedback.dto.EmployeePerformenceMasterDTO;
import ai.rnt.customerFeedback.enums.ApiResponse;

public interface EmployeePerformenceMasterService {

	ResponseEntity<EnumMap<ApiResponse, Object>> saveEmployeePerformenceMaster(
			EmployeePerformenceMasterDTO employeePerformenceMasterDTO);

	ResponseEntity<EnumMap<ApiResponse, Object>> getAllEmployeesPerformence();

	ResponseEntity<EnumMap<ApiResponse, Object>> getEmployeePerformenceById(Integer id);

	ResponseEntity<EnumMap<ApiResponse, Object>> updateEmployeePerformenceMaster(Integer id,
			EmployeePerformenceMasterDTO employeePerformenceMasterDTO);

	ResponseEntity<EnumMap<ApiResponse, Object>> saveEmployeePerformanceMail(
			EmployeePerformenceMailBoxDTO performenceMailBoxDTO);

	ResponseEntity<EnumMap<ApiResponse, Object>> getSavedEmployeePerformanceMail(int id);

}
