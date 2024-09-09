/**
 * 
 */
package ai.rnt.customerFeedback.controller;

import static ai.rnt.customerFeedback.constants.ApiConstants.BASE;
import static ai.rnt.customerFeedback.constants.ApiConstants.CUSTOMER_SATISFACTION_MASTER;
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

import ai.rnt.customerFeedback.dto.CustomerSatisfactionMailBoxDTO;
import ai.rnt.customerFeedback.dto.CustomerSatisfactionMasterDto;
import ai.rnt.customerFeedback.enums.ApiResponse;
import ai.rnt.customerFeedback.service.CustomerSatisfactionMasterService;
import ai.rnt.customerFeedback.util.EmailUtil;

/**
 * @author Nikita Raut
 * @Date Jan 12, 2024
 * @Version 1.0
 */

@RestController
@RequestMapping(BASE)
@CrossOrigin("*")

public class CustomerSatisfactionMasterController {

	@Autowired
	private CustomerSatisfactionMasterService customerSatisfactionMasterService;

	@Autowired
	private EmailUtil emailUtil;

	@PostMapping("send/{id}")
	public ResponseEntity<EnumMap<ApiResponse, Object>> sendEmail(@PathVariable int id) {
		return emailUtil.sendEmail(id);
	}

	@PostMapping(CUSTOMER_SATISFACTION_MASTER)
	public ResponseEntity<EnumMap<ApiResponse, Object>> saveCustomerSatisfactionMaster(
			@RequestBody CustomerSatisfactionMasterDto customerSatisfactionMasterDto) {

		return customerSatisfactionMasterService.saveCustomerSatisfactionMaster(customerSatisfactionMasterDto);
	}

	@GetMapping(CUSTOMER_SATISFACTION_MASTER + ID)
	public ResponseEntity<EnumMap<ApiResponse, Object>> getCustomerSatisfactionMasterById(@PathVariable Integer id) {

		return customerSatisfactionMasterService.getCustomerSatisfactionMasterById(id);
	}

	@GetMapping("addressMaster/{id}")
	public ResponseEntity<EnumMap<ApiResponse, Object>> getAddressMasterById(@PathVariable Integer id) {

		return customerSatisfactionMasterService.getAddressMasterById(id);
	}
//	@GetMapping("pseudoName/{name}")
//	public ResponseEntity<EnumMap<ApiResponse, Object>> getPseudoNameByCompanyName(@PathVariable String name) {
//		return customerSatisfactionMasterService.getPseudoName(name);
//	}

	@GetMapping(CUSTOMER_SATISFACTION_MASTER)
	public ResponseEntity<EnumMap<ApiResponse, Object>> getAllCustomerSatisfactionMaster() {

		return customerSatisfactionMasterService.getAllCustomerSatisfactionMaster();
	}

	@PutMapping(CUSTOMER_SATISFACTION_MASTER + ID)
	public ResponseEntity<EnumMap<ApiResponse, Object>> updateCustomerSatisfactionMaster(@PathVariable Integer id,
			@RequestBody CustomerSatisfactionMasterDto customerSatisfactionMasterDto) {

		return customerSatisfactionMasterService.updateCustomerSatisfactionMaster(id, customerSatisfactionMasterDto);
	}

	@DeleteMapping(CUSTOMER_SATISFACTION_MASTER + ID)
	public ResponseEntity<EnumMap<ApiResponse, Object>> deleteCustomerSatisfactionMaster(@PathVariable Integer id) {

		return customerSatisfactionMasterService.deleteCustomerSatisfactionMaster(id);
	}

	@PostMapping("saveCustomerSatisfactionMail")
	public ResponseEntity<EnumMap<ApiResponse, Object>> saveCustomerSatisfactionMailBox(
			@RequestBody CustomerSatisfactionMailBoxDTO customerSatisfactionMailBoxDTO) {
		return customerSatisfactionMasterService.saveCustomerSatisfactionMailBox(customerSatisfactionMailBoxDTO);
	}

	@GetMapping("getCustomerSatisfactionMail/{id}")
	public ResponseEntity<EnumMap<ApiResponse, Object>> getCustomerSatisfactionMail(@PathVariable Integer id) {
		return customerSatisfactionMasterService.getCustomerSatisfactionMail(id);
	}

}
