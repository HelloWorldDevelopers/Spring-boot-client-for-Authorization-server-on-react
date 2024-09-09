package ai.rnt.customerFeedback.dao.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ai.rnt.customerFeedback.entity.EmployeeMaster;
import ai.rnt.customerFeedback.exception.ResourceNotFoundException;
import ai.rnt.customerFeedback.repository.EmployeeMasterRepository;
@ExtendWith(MockitoExtension.class)
class EmployeeDaoServiceImplTest {

	@Mock
	EmployeeMasterRepository employeeMasterRepository;
	
	@InjectMocks
	EmployeeDaoServiceImpl employeeDaoServiceImpl;
	
	@Test
	void testGetEmployeebyUserId() {
		String userId = "ab1395";
		EmployeeMaster employeeMaster=new EmployeeMaster();
		employeeMaster.setUserId("ab1395");
		employeeMaster.setEmailId("a.bhor@rnt.ai");
		employeeMaster.setEmployeeJobTitle("developer");
		employeeMaster.setFirstName("akash");
		employeeMaster.setLastName("bhor");
		when(employeeMasterRepository.findByUserId(userId)).thenReturn(Optional.of(employeeMaster));
		assertNotNull(employeeDaoServiceImpl.getEmployeebyUserId(userId));
	}
	
	@Test
	void testGetById() {
		EmployeeMaster employeeMaster=new EmployeeMaster();
		employeeMaster.setUserId("ab1395");
		employeeMaster.setEmailId("a.bhor@rnt.ai");
		employeeMaster.setEmployeeJobTitle("developer");
		employeeMaster.setFirstName("akash");
		employeeMaster.setLastName("bhor");
		when(employeeMasterRepository.findById(anyInt())).thenReturn(Optional.of( new EmployeeMaster()));
		assertNotNull(employeeDaoServiceImpl.getById(anyInt()));
		when(employeeMasterRepository.findById(anyInt()))
		.thenThrow(ResourceNotFoundException.class);
		assertThrows(ResourceNotFoundException.class, () -> employeeDaoServiceImpl.getById(anyInt()));
	}

}
