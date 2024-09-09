package ai.rnt.customerFeedback.dto_mapper;

import static ai.rnt.customerFeedback.util.FunctionUtil.evalMapper;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import ai.rnt.customerFeedback.dto.CustomerDto;
import ai.rnt.customerFeedback.dto.McqOptionsDto;
import ai.rnt.customerFeedback.dto.ProjectDto;
import ai.rnt.customerFeedback.dto.QuestionMasterDto;
import ai.rnt.customerFeedback.entity.Customer;
import ai.rnt.customerFeedback.entity.McqOptions;
import ai.rnt.customerFeedback.entity.Project;
import ai.rnt.customerFeedback.entity.QuestionMaster;

public class QuestionDtoMapper {

	QuestionDtoMapper() {
	}

	public static final Function<QuestionMasterDto, Optional<QuestionMaster>> TO_QUESTION = e -> evalMapper(e,
			QuestionMaster.class);
	public static final Function<QuestionMaster, Optional<QuestionMasterDto>> TO_QUESTION_DTOS = e -> evalMapper(e,
			QuestionMasterDto.class);

	public static final Function<McqOptions, Optional<McqOptionsDto>> TO_MCQ_QUESTION_DTO = e -> evalMapper(e,
			McqOptionsDto.class);

	public static final Function<Collection<McqOptions>, List<McqOptionsDto>> TO_MCQ_QUESTIONS = e -> e.stream()
			.map(dm -> TO_MCQ_QUESTION_DTO.apply(dm).get()).collect(Collectors.toList());
	public static final Function<QuestionMaster, Optional<QuestionMasterDto>> TO_QUESTION_DTO = e -> {
		QuestionMasterDto employee = evalMapper(e, QuestionMasterDto.class).get();
		employee.setMcqOptionsDtos(TO_MCQ_QUESTIONS.apply(e.getMcqOptions()));
		return Optional.of(employee);
	};
	public static final Function<Collection<QuestionMaster>, List<QuestionMasterDto>> TO_QUESTIONS = e -> e.stream()
			.map(dm -> TO_QUESTION_DTO.apply(dm).get()).collect(Collectors.toList());

	public static final Function<Customer, Optional<CustomerDto>> TO_CUSTOMER = e -> evalMapper(e, CustomerDto.class);
	public static final Function<Collection<Customer>, List<CustomerDto>> TO_CUSTOMER_LIST = e -> e.stream()
			.map(dm -> TO_CUSTOMER.apply(dm).get()).collect(Collectors.toList());

	public static final Function<Project, Optional<ProjectDto>> TO_PROJECT_DTO = e -> evalMapper(e, ProjectDto.class);

	public static final Function<ProjectDto, Optional<Project>> TO_PROJECT = e -> evalMapper(e, Project.class);

	public static final Function<Collection<Project>, List<ProjectDto>> TO_PROJECT_LIST = e -> e.stream()
			.map(dm -> TO_PROJECT_DTO.apply(dm).get()).collect(Collectors.toList());

}
