package ai.rnt.customerFeedback.controller;

import static ai.rnt.customerFeedback.constants.ApiConstants.BASE;

import java.util.EnumMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ai.rnt.customerFeedback.dto.EmployeePerformenceMailBoxDTO;
import ai.rnt.customerFeedback.dto.EmployeePerformenceMasterDTO;
import ai.rnt.customerFeedback.enums.ApiResponse;
import ai.rnt.customerFeedback.service.EmployeePerformenceMasterService;
import ai.rnt.customerFeedback.service.EmployeeService;
import ai.rnt.customerFeedback.service.ProjectTeamService;
import ai.rnt.customerFeedback.service.impl.RoleServiceImpl;
import ai.rnt.customerFeedback.util.EmailUtil;

@RestController
@RequestMapping(BASE)
@CrossOrigin("*")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	RoleServiceImpl roleServiceImpl;

	@Autowired
	EmployeePerformenceMasterService employeePerformenceMasterService;

	@Autowired
	ProjectTeamService projectTeamService;

	@Autowired
	EmailUtil emailUtil;

	@GetMapping("employee")
	public ResponseEntity<EnumMap<ApiResponse, Object>> getAllEmployee() {
		return employeeService.getAllEmployees();
	}

	@GetMapping("role")
	public ResponseEntity<EnumMap<ApiResponse, Object>> getAllRoles() {
		return roleServiceImpl.getAllRoles();
	}

	@PostMapping(value = "employeePerformenceMaster")
	public ResponseEntity<EnumMap<ApiResponse, Object>> saveEmployeesPerformenceMaster(
			@RequestBody EmployeePerformenceMasterDTO employeePerformenceMasterDTO) {
		return employeePerformenceMasterService.saveEmployeePerformenceMaster(employeePerformenceMasterDTO);
	}

	@GetMapping("employeePerformenceMaster")
	public ResponseEntity<EnumMap<ApiResponse, Object>> getAllEmployeePerformence() {
		return employeePerformenceMasterService.getAllEmployeesPerformence();
	}

	@GetMapping("employeePerformenceMaster/{id}")
	public ResponseEntity<EnumMap<ApiResponse, Object>> getEmployeePerformenceById(@PathVariable int id) {
		return employeePerformenceMasterService.getEmployeePerformenceById(id);
	}

	@PutMapping(value = "employeePerformenceMaster/{id}")
	public ResponseEntity<EnumMap<ApiResponse, Object>> updateEmployeePerformenceMaster(@PathVariable("id") int id,
			@RequestBody EmployeePerformenceMasterDTO employeePerformenceMasterDTO) {
		return employeePerformenceMasterService.updateEmployeePerformenceMaster(id, employeePerformenceMasterDTO);
	}

	@GetMapping("projectlist/{staffid}")
	public ResponseEntity<EnumMap<ApiResponse, Object>> getProjectListStaffId(@PathVariable int staffid) {
		return projectTeamService.getProjectListByStaffId(staffid);
	}

	@PostMapping("projectManager")
	public ResponseEntity<EnumMap<ApiResponse, Object>> getProjectManagerListByProjectId(
			@RequestBody Map<String, Integer> map) {
		return projectTeamService.getProjectManagerListByProjectId(map.get("staffId"), map.get("projectId"),
				map.get("empPerformenceId"));
	}

	@GetMapping("employeeDetails/{staffid}")
	public ResponseEntity<EnumMap<ApiResponse, Object>> getEmployeeByStaffId(@PathVariable int staffid) {
		return employeeService.getEmployeeByStaffId(staffid);
	}

	@GetMapping("projectTeam/{id}")
	public ResponseEntity<EnumMap<ApiResponse, Object>> getprojectTeamDetailsById(@PathVariable int id) {
		return projectTeamService.getProjectTeamById(id);
	}

	@PostMapping("sendMailToManager/{id}")
	public ResponseEntity<EnumMap<ApiResponse, Object>> sendMailToManager(@PathVariable int id) {
		return emailUtil.sendEmailEmployeePerformence(id);
	}

	@GetMapping("profilePic/{id}")
	public ResponseEntity<EnumMap<ApiResponse, Object>> getProfilePic(@PathVariable int id) {
		return employeeService.getProfilePic(id);
	}

	@PostMapping("saveEmployeePerformanceMail")
	public ResponseEntity<EnumMap<ApiResponse, Object>> saveEmployeePerformanceMail(
			@RequestBody EmployeePerformenceMailBoxDTO performenceMailBoxDTO) {
		return employeePerformenceMasterService.saveEmployeePerformanceMail(performenceMailBoxDTO);
	}

	@GetMapping("getSavedEmployeeEprformanceEmail/{id}")
	public ResponseEntity<EnumMap<ApiResponse, Object>> getSavedEmployeePerformanceMail(@PathVariable int id) {
		return employeePerformenceMasterService.getSavedEmployeePerformanceMail(id);
	}

}
