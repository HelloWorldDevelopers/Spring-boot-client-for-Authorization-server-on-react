package ai.rnt.customerFeedback.controller;

import static ai.rnt.customerFeedback.constants.ApiConstants.ALL_CUSTOMER;
import static ai.rnt.customerFeedback.constants.ApiConstants.BASE;
import static ai.rnt.customerFeedback.constants.ApiConstants.PROJECT;
import static ai.rnt.customerFeedback.constants.ApiConstants.QUESTION;

import java.util.EnumMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ai.rnt.customerFeedback.enums.ApiResponse;
import ai.rnt.customerFeedback.service.QuestionService;

@RestController
@CrossOrigin("*")

@RequestMapping(BASE)
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@GetMapping(value = QUESTION)
	public ResponseEntity<EnumMap<ApiResponse, Object>> getAllQuestion() {
		return questionService.getAllQuestion();
	}
	
	@GetMapping(value= "question/{formType}")
	public ResponseEntity<EnumMap<ApiResponse, Object>> getAllQuestionForCustomerSatisfaction(@PathVariable int formType) {
		return questionService.getAllQuestionForCustomerSatisfaction(formType);
	}

	@GetMapping(value = QUESTION + ALL_CUSTOMER)
	public ResponseEntity<EnumMap<ApiResponse, Object>> getAllCustomer() {
		return questionService.getAllCustomer();
	}

	@GetMapping(value = QUESTION + PROJECT + "/{customerId}")
	public ResponseEntity<EnumMap<ApiResponse, Object>> getProjectListByCustomerId(@PathVariable int customerId) {
		return questionService.getProjectListByCustomerId(customerId);
	}
	
	@GetMapping("customer/{customerId}")
	public ResponseEntity<EnumMap<ApiResponse, Object>> getCustomerByCustomerId(@PathVariable int customerId) {
		return questionService.getAllCustomerContactPerson(customerId);
	}
	
	@GetMapping("getAllCustomersForSatisfaction")
	public ResponseEntity<EnumMap<ApiResponse, Object>> getAllCustomerForCustomerSatisfaction() {
		return questionService.getAllCustomersForSatisfaction();
	}

}
