package ai.rnt.customerFeedback.service;

import java.util.EnumMap;

import org.springframework.http.ResponseEntity;

import ai.rnt.customerFeedback.enums.ApiResponse;

public interface QuestionService {

	ResponseEntity<EnumMap<ApiResponse, Object>> getAllQuestion();

	ResponseEntity<EnumMap<ApiResponse, Object>> getAllQuestionForCustomerSatisfaction(int formType);

	ResponseEntity<EnumMap<ApiResponse, Object>> getAllCustomer();

	ResponseEntity<EnumMap<ApiResponse, Object>> getAllCustomerContactPerson(int customerId);

	ResponseEntity<EnumMap<ApiResponse, Object>> getProjectListByCustomerId(int id);

	ResponseEntity<EnumMap<ApiResponse, Object>> customerByCustomerId(int customerId);

	ResponseEntity<EnumMap<ApiResponse, Object>> getAllCustomersForSatisfaction();

}
