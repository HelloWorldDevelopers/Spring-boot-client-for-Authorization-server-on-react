package ai.rnt.customerFeedback.service.impl;

import static ai.rnt.customerFeedback.dto_mapper.CustomerSatisfactionMasterDtoMapper.TO_CUSTOMER_SATISFACTION_MASTER_DTO;
import static ai.rnt.customerFeedback.dto_mapper.CustomerSatisfactionSurveyDtoMapper.TO_CUSTOMER_SATISFACTION_SURVEY;
import static ai.rnt.customerFeedback.dto_mapper.CustomerSatisfactionSurveyDtoMapper.TO_CUSTOMER_SATISFACTION_SURVEY_DTO;
import static ai.rnt.customerFeedback.dto_mapper.CustomerSatisfactionSurveyDtoMapper.TO_CUSTOMER_SATIS_DTO_LIST;
import static ai.rnt.customerFeedback.dto_mapper.QuestionDtoMapper.TO_QUESTION_DTOS;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ai.rnt.customerFeedback.dto.CustomerSatisfactionSurveyDto;
import ai.rnt.customerFeedback.entity.CustomerSatisfactionSurvey;
import ai.rnt.customerFeedback.enums.ApiResponse;
import ai.rnt.customerFeedback.exception.CRMException;
import ai.rnt.customerFeedback.exception.ResourceNotFoundException;
import ai.rnt.customerFeedback.repository.CustomerSatisfactionMasterRepository;
import ai.rnt.customerFeedback.repository.CustomerSatisfactionSurveyRepository;
import ai.rnt.customerFeedback.repository.QuestionRepository;
import ai.rnt.customerFeedback.service.CustomerSatisfactionSurveyService;

@Service
public class CustomerSatisfactionSurveyServiceImpl implements CustomerSatisfactionSurveyService {

	@Autowired
	private CustomerSatisfactionSurveyRepository customerSatisfactionSurveyRepository;

	@Autowired
	private CustomerSatisfactionMasterRepository customerSatisfactionMasterRepository;

	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> saveCustomerSatisfactionSurvey(
			List<CustomerSatisfactionSurveyDto> customerSatisfactionSurveyDto) {

		List<CustomerSatisfactionSurvey> st = new ArrayList<>();
		EnumMap<ApiResponse, Object> map = new EnumMap<>(ApiResponse.class);
		try {
			List<CustomerSatisfactionSurvey> findById2 = customerSatisfactionSurveyRepository
					.findByCustomerSatisfactionMasterCustSatisfactionId(customerSatisfactionSurveyDto.get(0)
							.getCustomerSatisfactionMasterDto().getCustSatisfactionId());

			if (!findById2.isEmpty()) {
				map.put(ApiResponse.SUCCESS, false);
				return new ResponseEntity<>(map, HttpStatus.CREATED);
			} else {

				customerSatisfactionSurveyDto.stream().forEach(customerSatisfactionMaster -> {
					try {
						customerSatisfactionMaster
								.setCustomerSatisfactionMasterDto(TO_CUSTOMER_SATISFACTION_MASTER_DTO
										.apply(customerSatisfactionMasterRepository
												.findById(customerSatisfactionMaster.getCustomerSatisfactionMasterDto()
														.getCustSatisfactionId())
												.orElseThrow(ResourceNotFoundException::new))
										.orElseThrow(ResourceNotFoundException::new));
					} catch (Exception e) {
						throw new CRMException(e);
					}
					customerSatisfactionMaster
							.setQuestionMasterDto(
									TO_QUESTION_DTOS
											.apply(questionRepository
													.findById(customerSatisfactionMaster.getQuestionMasterDto()
															.getQuestionId())
													.orElseThrow(ResourceNotFoundException::new))
											.orElseThrow(ResourceNotFoundException::new));

					st.add(customerSatisfactionSurveyRepository.save(TO_CUSTOMER_SATISFACTION_SURVEY
							.apply(customerSatisfactionMaster).orElseThrow(ResourceNotFoundException::new)));
				});
				map.put(ApiResponse.SUCCESS, true);
				map.put(ApiResponse.DATA, st);
				return new ResponseEntity<>(map, HttpStatus.OK);
			}
		} catch (Exception e) {
			throw new CRMException(e);
		}
	}

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> getCustomerSatisfactionSurveyById(Integer id) {

		EnumMap<ApiResponse, Object> map = new EnumMap<>(ApiResponse.class);
		List<CustomerSatisfactionSurvey> findByCust = customerSatisfactionSurveyRepository
				.findByCustomerSatisfactionMasterCustSatisfactionIdOrderByQuestionMasterQuestionIdAsc(id);
		map.put(ApiResponse.SUCCESS, true);
		map.put(ApiResponse.DATA, TO_CUSTOMER_SATIS_DTO_LIST.apply(findByCust));
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> getAllCustomerSatisfactionSurvey() {

		EnumMap<ApiResponse, Object> map = new EnumMap<>(ApiResponse.class);

		map.put(ApiResponse.SUCCESS, true);
		map.put(ApiResponse.DATA, customerSatisfactionSurveyRepository.findAll().stream()
				.map(e -> TO_CUSTOMER_SATISFACTION_SURVEY_DTO.apply(e).orElseThrow(ResourceNotFoundException::new)));

		return new ResponseEntity<>(map, HttpStatus.OK);
	}

}
