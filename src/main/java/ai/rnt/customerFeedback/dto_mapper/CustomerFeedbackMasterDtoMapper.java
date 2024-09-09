/**
 * 
 */
package ai.rnt.customerFeedback.dto_mapper;

import static ai.rnt.customerFeedback.dto_mapper.QuestionDtoMapper.TO_PROJECT;
import static ai.rnt.customerFeedback.util.FunctionUtil.evalMapper;

import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import ai.rnt.customerFeedback.dto.CustomerFeedbackMailBoxDTO;
import ai.rnt.customerFeedback.dto.CustomerFeedbackMasterDTONew;
import ai.rnt.customerFeedback.dto.CustomerFeedbackMasterDto;
import ai.rnt.customerFeedback.dto.CustomerListDto;
import ai.rnt.customerFeedback.entity.CustomerFeedbackMailBox;
import ai.rnt.customerFeedback.entity.CustomerFeedbackMaster;
import ai.rnt.customerFeedback.exception.ResourceNotFoundException;

/**
 * @author Nikita Raut
 * @Date Jan 11, 2024
 * @Version 1.0
 */
public class CustomerFeedbackMasterDtoMapper {

	CustomerFeedbackMasterDtoMapper() {

	}

	public static final Function<CustomerFeedbackMaster, Optional<CustomerFeedbackMasterDto>> TO_CM_DTO = e -> evalMapper(
			e, CustomerFeedbackMasterDto.class);
	public static final Function<CustomerFeedbackMaster, Optional<CustomerFeedbackMasterDTONew>> TO_CM_NEW_DTO = e -> evalMapper(
			e, CustomerFeedbackMasterDTONew.class);
	public static final Function<CustomerFeedbackMasterDto, Optional<CustomerFeedbackMaster>> TO_CM = e -> evalMapper(e,
			CustomerFeedbackMaster.class);

	public static final Function<CustomerFeedbackMasterDto, Optional<CustomerFeedbackMaster>> TO_CUSTOMER_FEEDBACK_MASTER = e -> {
		CustomerFeedbackMaster employee = evalMapper(e, CustomerFeedbackMaster.class).get();
		employee.setProject(TO_PROJECT.apply(e.getProjectDto()).orElseThrow(ResourceNotFoundException::new));
		employee.setCreatedBy(1);
		return Optional.of(employee);
	};

	public static final Function<CustomerFeedbackMaster, Optional<CustomerListDto>> TO_CUSTOMERS_DTO = e -> {
		CustomerListDto employee = evalMapper(e, CustomerListDto.class).get();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		if (Objects.nonNull(e.getProject())) {
			employee.setProjectId(e.getProject().getProjectId());
			employee.setCustomerId(e.getProject().getCustomer().getCustomerId());
			employee.setCreatedDate(e.getCreatedDate().format(formatter));
			employee.setCompanyName(e.getProject().getCustomer().getCompanyName());
			employee.setProjectName(e.getProject().getProjectName());
			employee.setProjectAlias(e.getProject().getProjectAlias());
		}
		return Optional.of(employee);
	};

	public static final Function<Collection<CustomerFeedbackMaster>, List<CustomerListDto>> TO_CUSTOMER_LIST_DTO = e -> e
			.stream().map(dm -> TO_CUSTOMERS_DTO.apply(dm).get()).collect(Collectors.toList());

	public static final Function<CustomerFeedbackMailBoxDTO, Optional<CustomerFeedbackMailBox>> CUSTOMER_FEEDBACK_MAILBOX = e -> evalMapper(
			e, CustomerFeedbackMailBox.class);

	public static final Function<CustomerFeedbackMailBox, Optional<CustomerFeedbackMailBoxDTO>> CUSTOMER_FEEDBACK_MAILBOX_DTO = e -> evalMapper(
			e, CustomerFeedbackMailBoxDTO.class);
}
