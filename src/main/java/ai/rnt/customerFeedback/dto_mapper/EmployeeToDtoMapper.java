package ai.rnt.customerFeedback.dto_mapper;

import static ai.rnt.customerFeedback.dto_mapper.RoleDtoMapper.TO_Roles;
import static ai.rnt.customerFeedback.util.CollectionUtil.newList;
import static ai.rnt.customerFeedback.util.FunctionUtil.evalMapper;
import static ai.rnt.customerFeedback.util.FunctionUtil.evalMapperCollection;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import ai.rnt.customerFeedback.dto.EmployeeDto;
import ai.rnt.customerFeedback.dto.EmployeeMasterDTO;
import ai.rnt.customerFeedback.entity.EmployeeMaster;

public class EmployeeToDtoMapper {

	EmployeeToDtoMapper() {
	}

	public static final Function<EmployeeMaster, Optional<EmployeeDto>> TO_Employee = e -> {
		EmployeeDto employee = evalMapper(e, EmployeeDto.class).get();
		employee.setEmployeeRole(TO_Roles.apply(e.getEmployeeRole()));
		return Optional.of(employee);
	};

	public static final Function<Collection<EmployeeMaster>, Collection<EmployeeDto>> TO_Employees = e -> e.stream()
			.map(dm -> TO_Employee.apply(dm).get()).collect(Collectors.toList());

	public static final Function<EmployeeDto, Optional<EmployeeMaster>> TO_EmployeeMaster = e -> evalMapper(e,
			EmployeeMaster.class);

	public static final Function<EmployeeMaster, Optional<EmployeeMasterDTO>> TO_EMPLOYEEMASTER_DTO = e -> evalMapper(e,
			EmployeeMasterDTO.class);
	public static final Function<EmployeeMasterDTO, Optional<EmployeeMaster>> TO_EMPLOYEEMASTER = e -> evalMapper(e,
			EmployeeMaster.class);

	public static final Function<Collection<EmployeeMaster>, List<EmployeeMasterDTO>> TO_EMPLOYEE_MASTER_DTO_LIST = e -> newList(
			evalMapperCollection(e, EmployeeMasterDTO.class));

}
