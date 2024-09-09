package ai.rnt.customerFeedback.service;

import java.util.EnumMap;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import ai.rnt.customerFeedback.dto.EmployeeDto;
import ai.rnt.customerFeedback.entity.EmployeeMaster;
import ai.rnt.customerFeedback.enums.ApiResponse;

public interface EmployeeService {

	Optional<EmployeeDto> getEmployeeByUserId(String userId);

	Optional<EmployeeMaster> getById(Integer assignTo);

	ResponseEntity<EnumMap<ApiResponse, Object>> getAdminAndUser();

	ResponseEntity<EnumMap<ApiResponse, Object>> getAllEmployees();

	ResponseEntity<EnumMap<ApiResponse, Object>> getEmployeeByStaffId(Integer staffid);

	ResponseEntity<EnumMap<ApiResponse, Object>> getProfilePic(int id);

}
