/**
 * 
 */
package ai.rnt.customerFeedback.controller;

import static ai.rnt.customerFeedback.constants.ApiConstants.BASE;
import static ai.rnt.customerFeedback.constants.ApiConstants.CUSTOMER_FEEDBACK;
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

import ai.rnt.customerFeedback.dto.CustomerFeedbackDto;
import ai.rnt.customerFeedback.enums.ApiResponse;
import ai.rnt.customerFeedback.service.CustomerFeedbackService;

/**
 * @author Abhishek Ingle
 * @Date Jan 9, 2024
 * @Version 1.0
 */

@RestController
@RequestMapping(BASE)
@CrossOrigin("*")

public class CustomerFeedbackController {

	@Autowired
	private CustomerFeedbackService customerFeedbackService;

	@PostMapping(value = CUSTOMER_FEEDBACK)
	public ResponseEntity<EnumMap<ApiResponse, Object>> saveCustomerFeedback(
			@RequestBody List<CustomerFeedbackDto> customerFeedbackDto) {
		return customerFeedbackService.saveCustomerFeedback(customerFeedbackDto);
	}

	@GetMapping(value = CUSTOMER_FEEDBACK)
	public ResponseEntity<EnumMap<ApiResponse, Object>> getAllCustomerFeedback() {
		return customerFeedbackService.getAllCustomerFeedback();
	}

	@GetMapping(value = CUSTOMER_FEEDBACK + ID)
	public ResponseEntity<EnumMap<ApiResponse, Object>> getByCustomerFeedback(@PathVariable("id") int id) {
		return customerFeedbackService.getByCustomerFeedback(id);
	}

}
