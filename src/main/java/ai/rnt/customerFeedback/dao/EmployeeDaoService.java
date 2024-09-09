package ai.rnt.customerFeedback.dao;

import java.util.Optional;

import ai.rnt.customerFeedback.dto.EmployeeDto;
import ai.rnt.customerFeedback.entity.EmployeeMaster;

public interface EmployeeDaoService extends CrudService<EmployeeMaster, EmployeeDto> {

	Optional<EmployeeMaster> getEmployeebyUserId(String userId);

	@Override
	Optional<EmployeeDto> getById(Integer assignTo);

}
