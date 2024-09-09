package ai.rnt.customerFeedback.dto_mapper;

import static ai.rnt.customerFeedback.util.CollectionUtil.newList;
import static ai.rnt.customerFeedback.util.FunctionUtil.evalMapper;
import static ai.rnt.customerFeedback.util.FunctionUtil.evalMapperCollection;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import ai.rnt.customerFeedback.dto.EmployeePerformenceMailBoxDTO;
import ai.rnt.customerFeedback.dto.EmployeePerformenceMasterDTO;
import ai.rnt.customerFeedback.entity.EmployeePerformenceMailBox;
import ai.rnt.customerFeedback.entity.EmployeePerformenceMaster;

public class EmployeePerformenceMasterDTOMapper {
	private EmployeePerformenceMasterDTOMapper() {
	}

	public static final Function<EmployeePerformenceMasterDTO, Optional<EmployeePerformenceMaster>> TO_EMPLOYEE_PERFORMENCE_MASTER = e -> {
		EmployeePerformenceMaster employeePerformence = evalMapper(e, EmployeePerformenceMaster.class).get();
		employeePerformence.setCreatedBy(1);
		return Optional.of(employeePerformence);
	};

	public static final Function<EmployeePerformenceMaster, Optional<EmployeePerformenceMasterDTO>> TO_EMPLOYEE_PERFORMENCE_MASTER_DTO = e -> evalMapper(
			e, EmployeePerformenceMasterDTO.class);

	public static final Function<Collection<EmployeePerformenceMaster>, List<EmployeePerformenceMasterDTO>> TO_EMPLOYEE_PERFORMENCE_MASTER_DTO_LIST = e -> newList(
			evalMapperCollection(e, EmployeePerformenceMasterDTO.class));

	public static final Function<EmployeePerformenceMailBoxDTO, Optional<EmployeePerformenceMailBox>> EMPLOYEE_PERFORMANCE_MAILBOX = e -> evalMapper(
			e, EmployeePerformenceMailBox.class);

	public static final Function<EmployeePerformenceMailBox, Optional<EmployeePerformenceMailBoxDTO>> EMPLOYEE_PERFORMANCE_MAILBOX_DTO = e -> evalMapper(
			e, EmployeePerformenceMailBoxDTO.class);
}
