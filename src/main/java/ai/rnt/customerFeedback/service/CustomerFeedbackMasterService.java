package ai.rnt.customerFeedback.service;

import java.util.EnumMap;

import org.springframework.http.ResponseEntity;

import ai.rnt.customerFeedback.dto.CustomerFeedbackMailBoxDTO;
import ai.rnt.customerFeedback.dto.CustomerFeedbackMasterDto;
import ai.rnt.customerFeedback.enums.ApiResponse;

/**
 * @author Abhishek Ingle
 * @Date Jan 9, 2024
 * @Version 1.0
 */
public interface CustomerFeedbackMasterService {

	ResponseEntity<EnumMap<ApiResponse, Object>> saveCustomerFeedbackMaster(
			CustomerFeedbackMasterDto customerFeedbackMasterDto);

	ResponseEntity<EnumMap<ApiResponse, Object>> getCustomerFeedbackMaster(int id);

	ResponseEntity<EnumMap<ApiResponse, Object>> getAllCustomerFeedbackMaster();

	ResponseEntity<EnumMap<ApiResponse, Object>> updateCustomerFeedbackMaster(int id,
			CustomerFeedbackMasterDto customerListDto);

	ResponseEntity<EnumMap<ApiResponse, Object>> deleteCustomerFeedbackMaster(int id);

	ResponseEntity<EnumMap<ApiResponse, Object>> saveCustomerFeedbackMailBox(
			CustomerFeedbackMailBoxDTO customerFeedbackMailBoxDTO);

	ResponseEntity<EnumMap<ApiResponse, Object>> getCustomerFeedbackMail(Integer id);

}
