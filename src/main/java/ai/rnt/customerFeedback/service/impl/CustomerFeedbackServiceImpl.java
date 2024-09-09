/**
 * 
 */
package ai.rnt.customerFeedback.service.impl;

import static ai.rnt.customerFeedback.dto_mapper.CustomerFeedbackDtoMapper.TO_CUSTOMER_FEEDBACK;
import static ai.rnt.customerFeedback.dto_mapper.CustomerFeedbackDtoMapper.TO_CUSTOMER_FEEDBACK_DTO_LIST;
import static ai.rnt.customerFeedback.dto_mapper.CustomerFeedbackMasterDtoMapper.TO_CM_DTO;
import static ai.rnt.customerFeedback.dto_mapper.QuestionDtoMapper.TO_QUESTION_DTOS;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ai.rnt.customerFeedback.dto.CustomerFeedbackDto;
import ai.rnt.customerFeedback.entity.CustomerFeedback;
import ai.rnt.customerFeedback.enums.ApiResponse;
import ai.rnt.customerFeedback.exception.CRMException;
import ai.rnt.customerFeedback.exception.ResourceNotFoundException;
import ai.rnt.customerFeedback.repository.CustomerFeedbackMasterRepository;
import ai.rnt.customerFeedback.repository.CustomerFeedbackRepository;
import ai.rnt.customerFeedback.repository.QuestionRepository;
import ai.rnt.customerFeedback.service.CustomerFeedbackService;

/**
 * @author Abhishek Ingle
 * @Date Jan 9, 2024
 * @Version 1.0
 */
@Service
public class CustomerFeedbackServiceImpl implements CustomerFeedbackService {

	@Autowired
	private CustomerFeedbackRepository customerFeedbackRepository;

	@Autowired
	private CustomerFeedbackMasterRepository customerFeedbackMasterRepository;

	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> saveCustomerFeedback(
			List<CustomerFeedbackDto> customerFeedbackDtos) {
		List<CustomerFeedback> st = new ArrayList<>();
		EnumMap<ApiResponse, Object> resultMap = new EnumMap<>(ApiResponse.class);
		try {

			List<CustomerFeedback> findById2 = customerFeedbackRepository.findByCustomerFeedbackMasterCustFeedbackId(
					customerFeedbackDtos.get(0).getCustomerFeedbackMasterDto().getCustFeedbackId());
			if (!findById2.isEmpty()) {
				resultMap.put(ApiResponse.SUCCESS, false);
				return new ResponseEntity<>(resultMap, HttpStatus.CREATED);
			} else {

				customerFeedbackDtos.stream().forEach(customerFeedbackDto -> {
					customerFeedbackDto
							.setCustomerFeedbackMasterDto(
									TO_CM_DTO
											.apply(customerFeedbackMasterRepository
													.findById(customerFeedbackDto.getCustomerFeedbackMasterDto()
															.getCustFeedbackId())
													.orElseThrow(ResourceNotFoundException::new))
											.orElseThrow(ResourceNotFoundException::new));
					customerFeedbackDto
							.setQuestionMasterDto(
									TO_QUESTION_DTOS
											.apply(questionRepository
													.findById(
															customerFeedbackDto.getQuestionMasterDto().getQuestionId())
													.orElseThrow(ResourceNotFoundException::new))
											.orElseThrow(ResourceNotFoundException::new));

					st.add(customerFeedbackRepository.save(TO_CUSTOMER_FEEDBACK.apply(customerFeedbackDto)
							.orElseThrow(ResourceNotFoundException::new)));
				});
				resultMap.put(ApiResponse.SUCCESS, true);
				resultMap.put(ApiResponse.DATA, st);
				return new ResponseEntity<>(resultMap, HttpStatus.CREATED);
			}
		} catch (Exception e) {
			throw new CRMException(e);
		}

	}

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> getAllCustomerFeedback() {
		EnumMap<ApiResponse, Object> resultMap = new EnumMap<>(ApiResponse.class);
		try {
			resultMap.put(ApiResponse.SUCCESS, true);
			resultMap.put(ApiResponse.DATA, customerFeedbackRepository.findAll());
			return new ResponseEntity<>(resultMap, HttpStatus.FOUND);
		} catch (Exception e) {
			throw new CRMException(e);
		}
	}

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> getByCustomerFeedback(int id) {
		EnumMap<ApiResponse, Object> resultMap = new EnumMap<>(ApiResponse.class);
		try {
			List<CustomerFeedback> customerFeedbackList = customerFeedbackRepository
					.findByCustomerFeedbackMasterCustFeedbackIdOrderByQuestionMasterQuestionIdAsc(id);
			TO_CUSTOMER_FEEDBACK_DTO_LIST.apply(customerFeedbackList);
			resultMap.put(ApiResponse.SUCCESS, true);
			resultMap.put(ApiResponse.DATA, TO_CUSTOMER_FEEDBACK_DTO_LIST.apply(customerFeedbackList));
			return new ResponseEntity<>(resultMap, HttpStatus.OK);
		} catch (Exception e) {
			throw new CRMException(e);
		}
	}

}
