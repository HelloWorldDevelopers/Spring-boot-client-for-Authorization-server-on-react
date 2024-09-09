/**
 * 
 */
package ai.rnt.customerFeedback.service;

import java.util.EnumMap;
import java.util.List;

import org.springframework.http.ResponseEntity;

import ai.rnt.customerFeedback.dto.CustomerSatisfactionSurveyDto;
import ai.rnt.customerFeedback.enums.ApiResponse;

/**
 * @author Abhishek Ingle
 * @Date Jan 11, 2024
 * @Version 1.0
 */
public interface CustomerSatisfactionSurveyService {

	ResponseEntity<EnumMap<ApiResponse, Object>> saveCustomerSatisfactionSurvey(
			List<CustomerSatisfactionSurveyDto> customerSatisfactionSurveyDto);

	ResponseEntity<EnumMap<ApiResponse, Object>> getCustomerSatisfactionSurveyById(Integer id);

	ResponseEntity<EnumMap<ApiResponse, Object>> getAllCustomerSatisfactionSurvey();

}
