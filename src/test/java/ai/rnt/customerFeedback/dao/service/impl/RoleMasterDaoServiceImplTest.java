package ai.rnt.customerFeedback.dao.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ai.rnt.customerFeedback.entity.EmployeeMaster;
import ai.rnt.customerFeedback.repository.RoleMasterRepository;
@ExtendWith(MockitoExtension.class)
class RoleMasterDaoServiceImplTest {

	@Mock
	RoleMasterRepository roleMasterRepository;
	
	@InjectMocks
	RoleMasterDaoServiceImpl roleMasterDaoServiceImpl;
	
	@Test
	void testGetAdminAndUser() {
		EmployeeMaster employeeMaster=new EmployeeMaster();
		employeeMaster.setUserId("ab1395");
		employeeMaster.setEmailId("a.bhor@rnt.ai");
		employeeMaster.setEmployeeJobTitle("developer");
		employeeMaster.setFirstName("akash");
		employeeMaster.setLastName("bhor");
		List<EmployeeMaster> employeeList=new ArrayList<>();
		employeeList.add(employeeMaster);
		when(roleMasterRepository.findByEmployeeRoleIn(any())).thenReturn(employeeList);
		assertNotNull(roleMasterDaoServiceImpl.getAdminAndUser());
	}

}
