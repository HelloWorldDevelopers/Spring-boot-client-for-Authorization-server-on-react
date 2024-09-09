/**
 * 
 */
package ai.rnt.customerFeedback.service;

import java.util.EnumMap;
import java.util.List;

import org.springframework.http.ResponseEntity;

import ai.rnt.customerFeedback.dto.CustomerFeedbackDto;
import ai.rnt.customerFeedback.enums.ApiResponse;

/**
 * @author Abhishek Ingle
 * @Date Jan 9, 2024
 * @Version 1.0
 */
public interface CustomerFeedbackService {

	ResponseEntity<EnumMap<ApiResponse, Object>> saveCustomerFeedback(List<CustomerFeedbackDto> customerFeedbackDto);

	ResponseEntity<EnumMap<ApiResponse, Object>> getAllCustomerFeedback();

	ResponseEntity<EnumMap<ApiResponse, Object>> getByCustomerFeedback(int id);

}
