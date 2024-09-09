/**
 * 
 */
package ai.rnt.customerFeedback.controller;

import static ai.rnt.customerFeedback.constants.ApiConstants.BASE;
import static ai.rnt.customerFeedback.constants.ApiConstants.CUSTOMER_SATISFACTION_SURVEY;
import static ai.rnt.customerFeedback.constants.ApiConstants.ID;

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

import ai.rnt.customerFeedback.dto.CustomerSatisfactionSurveyDto;
import ai.rnt.customerFeedback.enums.ApiResponse;
import ai.rnt.customerFeedback.service.CustomerSatisfactionSurveyService;

/**
 * @author Abhishek Ingle
 * @Date Jan 12, 2024
 * @Version 1.0
 */

@RestController
@RequestMapping(BASE)
@CrossOrigin("*")

public class CustomerSatisfactionSurveyController {

	@Autowired
	CustomerSatisfactionSurveyService customerSatisfactionSurveyService;

	@PostMapping(CUSTOMER_SATISFACTION_SURVEY)
	public ResponseEntity<EnumMap<ApiResponse, Object>> saveCustomerSatisfactionSurvey(
			@RequestBody List<CustomerSatisfactionSurveyDto> customerSatisfactionSurveyDto) {

		return customerSatisfactionSurveyService.saveCustomerSatisfactionSurvey(customerSatisfactionSurveyDto);
	}

	@GetMapping(CUSTOMER_SATISFACTION_SURVEY + ID)
	public ResponseEntity<EnumMap<ApiResponse, Object>> getCustomerSatisfactionSurveyById(@PathVariable Integer id) {

		return customerSatisfactionSurveyService.getCustomerSatisfactionSurveyById(id);
	}

	@GetMapping(CUSTOMER_SATISFACTION_SURVEY)
	public ResponseEntity<EnumMap<ApiResponse, Object>> getAllCustomerSatisfactionSurvey() {

		return customerSatisfactionSurveyService.getAllCustomerSatisfactionSurvey();
	}

}
