package ai.rnt.customerFeedback.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ai.rnt.customerFeedback.dto.CustomerSatisfactionMailBoxDTO;
import ai.rnt.customerFeedback.dto.EmployeePerformenceMailBoxDTO;
import ai.rnt.customerFeedback.dto.EmployeePerformenceMasterDTO;
import ai.rnt.customerFeedback.enums.ApiResponse;
import ai.rnt.customerFeedback.service.EmployeePerformenceMasterService;
import ai.rnt.customerFeedback.service.EmployeeService;
import ai.rnt.customerFeedback.service.ProjectTeamService;
import ai.rnt.customerFeedback.service.impl.RoleServiceImpl;
import ai.rnt.customerFeedback.util.EmailUtil;

@ExtendWith(MockitoExtension.class)

class EmployeeControllerTest {
	@Mock
	EmailUtil emailUtil;

	@Mock
	private EmployeeService employeeService;

	@Mock
	private RoleServiceImpl roleServiceImpl;

	@Mock
	ProjectTeamService projectTeamService;

	@Mock
	EmployeePerformenceMasterService employeePerformenceMasterService;

	@InjectMocks
	private EmployeeController employeeController;

	@Test
	void testGetAllEmployee() {
		EnumMap<ApiResponse, Object> mockResponse = new EnumMap<>(ApiResponse.class);
		when(employeeService.getAllEmployees()).thenReturn(ResponseEntity.ok(mockResponse));
		ResponseEntity<EnumMap<ApiResponse, Object>> responseEntity = employeeController.getAllEmployee();
		assertEquals(mockResponse, responseEntity.getBody());
	}

	@Test
	void testGetAllRoles() {
		EnumMap<ApiResponse, Object> mockResponse = new EnumMap<>(ApiResponse.class);
		when(roleServiceImpl.getAllRoles()).thenReturn(ResponseEntity.ok(mockResponse));
		ResponseEntity<EnumMap<ApiResponse, Object>> responseEntity = employeeController.getAllRoles();
		assertEquals(mockResponse, responseEntity.getBody());

	}

	@Test
	void testGetAllEmployeePerformence() {
		EnumMap<ApiResponse, Object> mockResponse = new EnumMap<>(ApiResponse.class);
		when(employeePerformenceMasterService.getAllEmployeesPerformence()).thenReturn(ResponseEntity.ok(mockResponse));
		ResponseEntity<EnumMap<ApiResponse, Object>> responseEntity = employeeController.getAllEmployeePerformence();
		assertEquals(mockResponse, responseEntity.getBody());

	}

	@Test
	void testSaveEmployeesPerformenceMaster() {
		EmployeePerformenceMasterDTO employeePerformenceMasterDTO = new EmployeePerformenceMasterDTO();
		ResponseEntity<EnumMap<ApiResponse, Object>> expectedResult = createSampleResponseEntity();
		when(employeePerformenceMasterService.saveEmployeePerformenceMaster(employeePerformenceMasterDTO))
				.thenReturn(expectedResult);
		ResponseEntity<EnumMap<ApiResponse, Object>> response = employeeController
				.saveEmployeesPerformenceMaster(employeePerformenceMasterDTO);
		assertEquals(expectedResult, response);
	}

	private ResponseEntity<EnumMap<ApiResponse, Object>> createSampleResponseEntity() {
		EnumMap<ApiResponse, Object> responseMap = new EnumMap<>(ApiResponse.class);
		responseMap.put(ApiResponse.SUCCESS, true);
		responseMap.put(ApiResponse.DATA, "Sample Data");
		return new ResponseEntity<>(responseMap, HttpStatus.OK);
	}

	@Test
	void testGetEmployeePerformenceById() {
		EnumMap<ApiResponse, Object> mockResponse = new EnumMap<>(ApiResponse.class);
		when(employeePerformenceMasterService.getEmployeePerformenceById(anyInt()))
				.thenReturn(ResponseEntity.ok(mockResponse));
		ResponseEntity<EnumMap<ApiResponse, Object>> responseEntity = employeeController
				.getEmployeePerformenceById(anyInt());
		assertEquals(mockResponse, responseEntity.getBody());

	}

	@Test
	void testUpdateEmployeePerformenceMaster_Success() {
		int id = 1;
		EmployeePerformenceMasterDTO employeePerformenceMasterDTO = new EmployeePerformenceMasterDTO();
		EnumMap<ApiResponse, Object> responseMap = new EnumMap<>(ApiResponse.class);
		responseMap.put(ApiResponse.SUCCESS, true);
		responseMap.put(ApiResponse.MESSAGE, "updated");
		ResponseEntity<EnumMap<ApiResponse, Object>> expectedResponse = new ResponseEntity<>(responseMap,
				HttpStatus.OK);
		when(employeePerformenceMasterService.updateEmployeePerformenceMaster(id, employeePerformenceMasterDTO))
				.thenReturn(expectedResponse);
		ResponseEntity<EnumMap<ApiResponse, Object>> actualResponse = employeeController
				.updateEmployeePerformenceMaster(id, employeePerformenceMasterDTO);
		assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
		assertEquals(expectedResponse.getBody(), actualResponse.getBody());
	}

	@Test
	void testGetProjectListStaffId() {
		EnumMap<ApiResponse, Object> mockResponse = new EnumMap<>(ApiResponse.class);
		when(projectTeamService.getProjectListByStaffId(anyInt())).thenReturn(ResponseEntity.ok(mockResponse));
		ResponseEntity<EnumMap<ApiResponse, Object>> responseEntity = employeeController
				.getProjectListStaffId(anyInt());
		assertEquals(mockResponse, responseEntity.getBody());

	}

	@Test
	void testgetEmployeeByStaffId() {
		EnumMap<ApiResponse, Object> mockResponse = new EnumMap<>(ApiResponse.class);
		when(employeeService.getEmployeeByStaffId(anyInt())).thenReturn(ResponseEntity.ok(mockResponse));
		ResponseEntity<EnumMap<ApiResponse, Object>> responseEntity = employeeController.getEmployeeByStaffId(anyInt());
		assertEquals(mockResponse, responseEntity.getBody());

	}

	@Test
	void testSendMailToManager() {
		int id = 1;
		ResponseEntity<EnumMap<ApiResponse, Object>> expectedResult = createSampleResponseEntity();

		when(emailUtil.sendEmailEmployeePerformence(id)).thenReturn(expectedResult);

		ResponseEntity<EnumMap<ApiResponse, Object>> response = employeeController.sendMailToManager(id);
		assertEquals(expectedResult, response);
	}

	@Test
	void testgetprojectTeamDetailsById() {
		EnumMap<ApiResponse, Object> mockResponse = new EnumMap<>(ApiResponse.class);
		when(projectTeamService.getProjectTeamById(anyInt())).thenReturn(ResponseEntity.ok(mockResponse));
		ResponseEntity<EnumMap<ApiResponse, Object>> responseEntity = employeeController
				.getprojectTeamDetailsById(anyInt());
		assertEquals(mockResponse, responseEntity.getBody());

	}

	@Test
	void testgetProfilePic() {
		EnumMap<ApiResponse, Object> mockResponse = new EnumMap<>(ApiResponse.class);
		when(employeeService.getProfilePic(anyInt())).thenReturn(ResponseEntity.ok(mockResponse));
		ResponseEntity<EnumMap<ApiResponse, Object>> responseEntity = employeeController.getProfilePic(anyInt());
		assertEquals(mockResponse, responseEntity.getBody());

	}

	@Test
	void testgetProjectManagerListByProjectId() {
		int id = 1;
		ResponseEntity<EnumMap<ApiResponse, Object>> expectedResult = createSampleResponseEntity();

		when(projectTeamService.getProjectManagerListByProjectId(id, id, id)).thenReturn(expectedResult);
		Map<String, Integer> map = new HashMap<>();
		map.put("staffId", 1);
		map.put("projectId", 1);
		map.put("empPerformenceId", 1);

		ResponseEntity<EnumMap<ApiResponse, Object>> response = employeeController
				.getProjectManagerListByProjectId(map);
		assertEquals(expectedResult, response);
	}

	@Test
	void testsaveEmployeePerformanceMail() {
		EnumMap<ApiResponse, Object> mockResponse = new EnumMap<>(ApiResponse.class);
		EmployeePerformenceMailBoxDTO vendorDTO = new EmployeePerformenceMailBoxDTO();
		when(employeePerformenceMasterService.saveEmployeePerformanceMail(vendorDTO))
				.thenReturn(ResponseEntity.ok(mockResponse));
		ResponseEntity<EnumMap<ApiResponse, Object>> responseEntity = employeeController
				.saveEmployeePerformanceMail(vendorDTO);
		assertEquals(mockResponse, responseEntity.getBody());
	}

	@Test
	void testgetSavedEmployeePerformanceMail() {
		EnumMap<ApiResponse, Object> mockResponse = new EnumMap<>(ApiResponse.class);
		when(employeePerformenceMasterService.getSavedEmployeePerformanceMail(anyInt()))
				.thenReturn(ResponseEntity.ok(mockResponse));
		ResponseEntity<EnumMap<ApiResponse, Object>> responseEntity = employeeController
				.getSavedEmployeePerformanceMail(anyInt());
		assertEquals(mockResponse, responseEntity.getBody());

	}

}
