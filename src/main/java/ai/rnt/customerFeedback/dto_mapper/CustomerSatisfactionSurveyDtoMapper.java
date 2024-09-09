package ai.rnt.customerFeedback.dto_mapper;

import static ai.rnt.customerFeedback.dto_mapper.CustomerSatisfactionMasterDtoMapper.TO_CUSTOMER_SATISFACTION_MASTER;
import static ai.rnt.customerFeedback.dto_mapper.CustomerSatisfactionMasterDtoMapper.TO_CUSTOMER_SATISFACTION_MASTER_DTO;
import static ai.rnt.customerFeedback.dto_mapper.QuestionDtoMapper.TO_QUESTION;
import static ai.rnt.customerFeedback.dto_mapper.QuestionDtoMapper.TO_QUESTION_DTO;
import static ai.rnt.customerFeedback.util.CollectionUtil.newList;
import static ai.rnt.customerFeedback.util.FunctionUtil.evalMapper;
import static ai.rnt.customerFeedback.util.FunctionUtil.evalMapperCollection;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import ai.rnt.customerFeedback.dto.CustoSatisSurveyDTO;
import ai.rnt.customerFeedback.dto.CustomerSatisfactionSurveyDto;
import ai.rnt.customerFeedback.entity.CustomerSatisfactionSurvey;
import ai.rnt.customerFeedback.exception.ResourceNotFoundException;

/**
 * @author Nikita Raut
 * @Date Jan 11, 2024
 * @Version 1.0
 */
public class CustomerSatisfactionSurveyDtoMapper {

	private CustomerSatisfactionSurveyDtoMapper() {

	}

	public static final Function<CustomerSatisfactionSurveyDto, Optional<CustomerSatisfactionSurvey>> TO_CUSTOMER_SATISFACTION_SURVEY = e -> {
		CustomerSatisfactionSurvey customerSatisfactionSurvey = evalMapper(e, CustomerSatisfactionSurvey.class).get();
		customerSatisfactionSurvey.setQuestionMaster(
				TO_QUESTION.apply(e.getQuestionMasterDto()).orElseThrow(ResourceNotFoundException::new));
		customerSatisfactionSurvey.setCustomerSatisfactionMaster(TO_CUSTOMER_SATISFACTION_MASTER
				.apply(e.getCustomerSatisfactionMasterDto()).orElseThrow(ResourceNotFoundException::new));
		return Optional.of(customerSatisfactionSurvey);
	};

	public static final Function<CustomerSatisfactionSurvey, Optional<CustomerSatisfactionSurveyDto>> TO_CUSTOMER_SATISFACTION_SURVEY_DTO = e -> {
		CustomerSatisfactionSurveyDto customerSatisfactionSurveyDto = evalMapper(e, CustomerSatisfactionSurveyDto.class)
				.get();
		customerSatisfactionSurveyDto.setQuestionMasterDto(
				TO_QUESTION_DTO.apply(e.getQuestionMaster()).orElseThrow(ResourceNotFoundException::new));
		customerSatisfactionSurveyDto.setCustomerSatisfactionMasterDto(TO_CUSTOMER_SATISFACTION_MASTER_DTO
				.apply(e.getCustomerSatisfactionMaster()).orElseThrow(ResourceNotFoundException::new));
		return Optional.of(customerSatisfactionSurveyDto);
	};

	public static final Function<Collection<CustomerSatisfactionSurvey>, List<CustoSatisSurveyDTO>> TO_CUSTOMER_SATIS_DTO_LIST = e -> newList(
			evalMapperCollection(e, CustoSatisSurveyDTO.class));

}
