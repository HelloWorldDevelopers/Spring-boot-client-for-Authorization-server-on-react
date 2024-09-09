/**
 * 
 */
package ai.rnt.customerFeedback.controller;

import static ai.rnt.customerFeedback.constants.ApiConstants.BASE;
import static ai.rnt.customerFeedback.constants.ApiConstants.CUSTOMER_FEEDBACK_MASTER;
import static ai.rnt.customerFeedback.constants.ApiConstants.ID;

import java.util.EnumMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ai.rnt.customerFeedback.dto.CustomerFeedbackMailBoxDTO;
import ai.rnt.customerFeedback.dto.CustomerFeedbackMasterDto;
import ai.rnt.customerFeedback.enums.ApiResponse;
import ai.rnt.customerFeedback.service.CustomerFeedbackMasterService;
import ai.rnt.customerFeedback.util.EmailUtil;

/**
 * @author Abhishek Ingle
 * @Date Jan 9, 2024
 * @Version 1.0
 */

@RestController
@CrossOrigin("*")
@RequestMapping(BASE)
public class CustomerFeedbackMasterController {
	
	@Autowired
	EmailUtil emailUtil;

	@Autowired
	private CustomerFeedbackMasterService customerFeedbackMasterService;

	@PostMapping(value = CUSTOMER_FEEDBACK_MASTER)
	public ResponseEntity<EnumMap<ApiResponse, Object>> saveCustomerFeedbackMaster(
			@RequestBody CustomerFeedbackMasterDto customerFeedbackMasterDto) {
		return customerFeedbackMasterService.saveCustomerFeedbackMaster(customerFeedbackMasterDto);
	}

	@GetMapping(value = CUSTOMER_FEEDBACK_MASTER + ID)
	public ResponseEntity<EnumMap<ApiResponse, Object>> getCustomerFeedbackMaster(@PathVariable("id") int id) {
		return customerFeedbackMasterService.getCustomerFeedbackMaster(id);
	}

	@GetMapping(value = CUSTOMER_FEEDBACK_MASTER)
	public ResponseEntity<EnumMap<ApiResponse, Object>> getAllCustomerFeedbackMaster() {
		return customerFeedbackMasterService.getAllCustomerFeedbackMaster();
	}

	@PutMapping(value = CUSTOMER_FEEDBACK_MASTER + ID)
	public ResponseEntity<EnumMap<ApiResponse, Object>> updateCustomerFeedbackMaster(@PathVariable("id") int id,
			@RequestBody CustomerFeedbackMasterDto customerListDto) {
		return customerFeedbackMasterService.updateCustomerFeedbackMaster(id, customerListDto);
	}

	@DeleteMapping(value = CUSTOMER_FEEDBACK_MASTER + ID)
	public ResponseEntity<EnumMap<ApiResponse, Object>> deleteCustomerFeedbackMaster(@PathVariable("id") int id) {
		return customerFeedbackMasterService.deleteCustomerFeedbackMaster(id);
	}
	
	@PostMapping("sendMailToProjectEnd/{id}")
    public ResponseEntity<EnumMap<ApiResponse, Object>> sendMailToManager(@PathVariable int id) {
    	return emailUtil.sendEmailProjectEndFeedback(id);
    }
	
	@PostMapping("saveCustomerFeedbackMail")
	public ResponseEntity<EnumMap<ApiResponse, Object>> saveCustomerFeedbackMasterMail(@RequestBody CustomerFeedbackMailBoxDTO customerFeedbackMailBoxDTO) {
		return customerFeedbackMasterService.saveCustomerFeedbackMailBox(customerFeedbackMailBoxDTO);
	}
	
	@GetMapping("getCustomerFeedbackMail/{id}")
	public ResponseEntity<EnumMap<ApiResponse, Object>> getCustomerFeedbackMail(@PathVariable int id) {
		return customerFeedbackMasterService.getCustomerFeedbackMail(id);
	}

}
