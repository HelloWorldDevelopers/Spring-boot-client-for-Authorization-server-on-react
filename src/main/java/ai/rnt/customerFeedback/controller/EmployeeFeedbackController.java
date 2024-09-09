package ai.rnt.customerFeedback.controller;

import static ai.rnt.customerFeedback.constants.ApiConstants.BASE;

import java.util.EnumMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ai.rnt.customerFeedback.dto.EmployeePerformenceFeedbackDTO;
import ai.rnt.customerFeedback.enums.ApiResponse;
import ai.rnt.customerFeedback.service.EmployeePerformenceFeedbackService;

@RestController
@RequestMapping(BASE)
@CrossOrigin("*")

public class EmployeeFeedbackController {

	@Autowired
	EmployeePerformenceFeedbackService employeePerformenceFeedbackService;

	@PostMapping("employeeFeedback")
	public ResponseEntity<EnumMap<ApiResponse, Object>> saveEmployeeFeedback(
			@RequestBody List<EmployeePerformenceFeedbackDTO> employeePerformenceFeedbackDTO) {

		return employeePerformenceFeedbackService.saveEmployeePerformenceFeedback(employeePerformenceFeedbackDTO);
	}

	@GetMapping("employeeFeedback")
	public ResponseEntity<EnumMap<ApiResponse, Object>> getAllPerformenceFeedback() {
		return employeePerformenceFeedbackService.getAllEmployeePerformenceFeedback();
	}

	@GetMapping("employeeFeedback/{id}")
	public ResponseEntity<EnumMap<ApiResponse, Object>> getPerformenceFeedbackById(@PathVariable Integer id) {
		return employeePerformenceFeedbackService.getEmployeePerformenceFeedbackById(id);
	}

}
