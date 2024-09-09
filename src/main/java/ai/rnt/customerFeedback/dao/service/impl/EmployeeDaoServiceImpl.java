package ai.rnt.customerFeedback.dao.service.impl;

import static ai.rnt.customerFeedback.dto_mapper.EmployeeToDtoMapper.TO_Employee;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ai.rnt.customerFeedback.dao.EmployeeDaoService;
import ai.rnt.customerFeedback.dto.EmployeeDto;
import ai.rnt.customerFeedback.entity.EmployeeMaster;
import ai.rnt.customerFeedback.exception.ResourceNotFoundException;
import ai.rnt.customerFeedback.repository.EmployeeMasterRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeDaoServiceImpl implements EmployeeDaoService{

	private final EmployeeMasterRepository employeeMasterRepository;
	
	@Override
	public Optional<EmployeeMaster> getEmployeebyUserId(String userId) {
		return employeeMasterRepository.findByUserId(userId);
	}

	@Override
	public Optional<EmployeeDto> getById(Integer assignTo) {
		return TO_Employee.apply(employeeMasterRepository.findById(assignTo).orElseThrow(()->new ResourceNotFoundException("Employee", "staffId", assignTo)));
	}


}
