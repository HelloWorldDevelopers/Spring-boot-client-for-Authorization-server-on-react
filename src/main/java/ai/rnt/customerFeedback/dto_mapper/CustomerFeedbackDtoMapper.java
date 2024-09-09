package ai.rnt.customerFeedback.dto_mapper;

import static ai.rnt.customerFeedback.dto_mapper.CustomerFeedbackMasterDtoMapper.TO_CM;
import static ai.rnt.customerFeedback.dto_mapper.QuestionDtoMapper.TO_QUESTION;
import static ai.rnt.customerFeedback.util.CollectionUtil.newList;
import static ai.rnt.customerFeedback.util.FunctionUtil.evalMapper;
import static ai.rnt.customerFeedback.util.FunctionUtil.evalMapperCollection;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import ai.rnt.customerFeedback.dto.CustomerFeedbackDto;
import ai.rnt.customerFeedback.dto.CustomerFeedbackNewDTO;
import ai.rnt.customerFeedback.entity.CustomerFeedback;
import ai.rnt.customerFeedback.exception.ResourceNotFoundException;

/**
 * @author Nikita Raut
 * @Date Jan 11, 2024
 * @Version 1.0
 */
public class CustomerFeedbackDtoMapper {

	CustomerFeedbackDtoMapper() {

	}

	public static final Function<CustomerFeedbackDto, Optional<CustomerFeedback>> TO_CUSTOMER_FEEDBACK = e -> {
		CustomerFeedback employee = evalMapper(e, CustomerFeedback.class).get();
		employee.setQuestionMaster(
				TO_QUESTION.apply(e.getQuestionMasterDto()).orElseThrow(ResourceNotFoundException::new));
		employee.setCustomerFeedbackMaster(
				TO_CM.apply(e.getCustomerFeedbackMasterDto()).orElseThrow(ResourceNotFoundException::new));
		return Optional.of(employee);
	};

	public static final Function<Collection<CustomerFeedback>, List<CustomerFeedbackNewDTO>> TO_CUSTOMER_FEEDBACK_DTO_LIST = e -> newList(
			evalMapperCollection(e, CustomerFeedbackNewDTO.class));

}
