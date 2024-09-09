/**
 * 
 */
package ai.rnt.customerFeedback.service;

import java.util.EnumMap;

import org.springframework.http.ResponseEntity;

import ai.rnt.customerFeedback.dto.CustomerSatisfactionMailBoxDTO;
import ai.rnt.customerFeedback.dto.CustomerSatisfactionMasterDto;
import ai.rnt.customerFeedback.enums.ApiResponse;

/**
 * @author Abhishek Ingle
 * @Date Jan 11, 2024
 * @Version 1.0
 */
public interface CustomerSatisfactionMasterService {

	ResponseEntity<EnumMap<ApiResponse, Object>> saveCustomerSatisfactionMaster(
			CustomerSatisfactionMasterDto customerSatisfactionMasterDto);

	ResponseEntity<EnumMap<ApiResponse, Object>> getCustomerSatisfactionMasterById(Integer id);

	ResponseEntity<EnumMap<ApiResponse, Object>> getAddressMasterById(Integer id);

	ResponseEntity<EnumMap<ApiResponse, Object>> getAllCustomerSatisfactionMaster();

	ResponseEntity<EnumMap<ApiResponse, Object>> updateCustomerSatisfactionMaster(Integer id,
			CustomerSatisfactionMasterDto customerSatisfactionMasterDto);

	ResponseEntity<EnumMap<ApiResponse, Object>> deleteCustomerSatisfactionMaster(Integer id);

	ResponseEntity<EnumMap<ApiResponse, Object>> saveCustomerSatisfactionMailBox(
			CustomerSatisfactionMailBoxDTO satisfactionMailBoxDTO);

	ResponseEntity<EnumMap<ApiResponse, Object>> getCustomerSatisfactionMail(Integer id);

}
