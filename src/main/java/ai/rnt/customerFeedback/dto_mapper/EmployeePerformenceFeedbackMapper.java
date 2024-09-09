package ai.rnt.customerFeedback.dto_mapper;

import static ai.rnt.customerFeedback.dto_mapper.EmployeePerformenceMasterDTOMapper.TO_EMPLOYEE_PERFORMENCE_MASTER;
import static ai.rnt.customerFeedback.dto_mapper.EmployeePerformenceMasterDTOMapper.TO_EMPLOYEE_PERFORMENCE_MASTER_DTO;
import static ai.rnt.customerFeedback.dto_mapper.QuestionDtoMapper.TO_QUESTION;
import static ai.rnt.customerFeedback.dto_mapper.QuestionDtoMapper.TO_QUESTION_DTO;
import static ai.rnt.customerFeedback.util.CollectionUtil.newList;
import static ai.rnt.customerFeedback.util.FunctionUtil.evalMapper;
import static ai.rnt.customerFeedback.util.FunctionUtil.evalMapperCollection;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import ai.rnt.customerFeedback.dto.EmployeeFeedbackDTO;
import ai.rnt.customerFeedback.dto.EmployeePerformenceFeedbackDTO;
import ai.rnt.customerFeedback.entity.EmployeePerformenceFeedback;
import ai.rnt.customerFeedback.exception.ResourceNotFoundException;

public class EmployeePerformenceFeedbackMapper {
	EmployeePerformenceFeedbackMapper() {
	}

	public static final Function<Collection<EmployeePerformenceFeedback>, List<EmployeeFeedbackDTO>> TO_EMP_PERF_FEEDBACK_DTO_LIST = e -> newList(
			evalMapperCollection(e, EmployeeFeedbackDTO.class));

	public static final Function<EmployeePerformenceFeedback, Optional<EmployeePerformenceFeedbackDTO>> TO_EMPLOYEE_PERFORMENCE_FEEDBACK_DTO = e -> {
		EmployeePerformenceFeedbackDTO employeePerformenceFeedbackDTO = evalMapper(e,
				EmployeePerformenceFeedbackDTO.class).get();
		employeePerformenceFeedbackDTO.setQuestionMaster(
				TO_QUESTION_DTO.apply(e.getQuestionMaster()).orElseThrow(ResourceNotFoundException::new));
		employeePerformenceFeedbackDTO.setEmployeePerformenceMaster(TO_EMPLOYEE_PERFORMENCE_MASTER_DTO
				.apply(e.getEmployeePerformenceMaster()).orElseThrow(ResourceNotFoundException::new));
		return Optional.of(employeePerformenceFeedbackDTO);
	};

	public static final Function<EmployeePerformenceFeedbackDTO, Optional<EmployeePerformenceFeedback>> TO_EMP_FEEDBACK = e -> {
		EmployeePerformenceFeedback customerSatisfactionSurvey = evalMapper(e, EmployeePerformenceFeedback.class).get();
		customerSatisfactionSurvey.setQuestionMaster(
				TO_QUESTION.apply(e.getQuestionMaster()).orElseThrow(ResourceNotFoundException::new));
		customerSatisfactionSurvey.setEmployeePerformenceMaster(TO_EMPLOYEE_PERFORMENCE_MASTER
				.apply(e.getEmployeePerformenceMaster()).orElseThrow(ResourceNotFoundException::new));
		return Optional.of(customerSatisfactionSurvey);
	};
}
