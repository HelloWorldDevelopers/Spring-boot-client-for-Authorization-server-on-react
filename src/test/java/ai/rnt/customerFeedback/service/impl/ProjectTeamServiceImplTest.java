package ai.rnt.customerFeedback.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ai.rnt.customerFeedback.entity.EmployeeMaster;
import ai.rnt.customerFeedback.entity.Project;
import ai.rnt.customerFeedback.enums.ApiResponse;
import ai.rnt.customerFeedback.exception.CRMException;
import ai.rnt.customerFeedback.repository.EmployeeMasterRepository;
import ai.rnt.customerFeedback.repository.EmployeePerformenceMasterRepository;
import ai.rnt.customerFeedback.repository.ProjectRepository;
import ai.rnt.customerFeedback.repository.ProjectTeamRepository;

@ExtendWith(MockitoExtension.class)
class ProjectTeamServiceImplTest {
	@Mock
	EmployeeMasterRepository employeeMasterRepository;

	@Mock
	EmployeePerformenceMasterRepository employeePerformenceMasterRepository;

	@Mock
	ProjectTeamRepository projectTeamRepository;

	@InjectMocks
	ProjectTeamServiceImpl projectTeamServiceImpl;

	@Mock
	ProjectRepository projectRepository;

	private Project project;

	@BeforeEach
	public void setUp() {
		project = new Project();
		project.setProjectManager(1);
	}

	@Test
	void testGetProjectListByStaffId() {
		int staffId = 1;
		List<Project> project = new ArrayList<>();
		when(projectTeamRepository.findByEmployeeMasterStaffId(staffId)).thenReturn(project);
		assertNotNull(projectTeamServiceImpl.getProjectListByStaffId(staffId));
		when(projectTeamRepository.findByEmployeeMasterStaffId(staffId)).thenThrow(CRMException.class);
		assertThrows(CRMException.class, () -> projectTeamServiceImpl.getProjectListByStaffId(staffId));
	}

	@Test
	void testGetProjectManagerListByProjectId_ProjectExists() {
		List<EmployeeMaster> employeeMasters = Arrays.asList(new EmployeeMaster(1, "John", "Doe", null),
				new EmployeeMaster(2, "Jane", "Smith", null));
		when(projectRepository.findById(anyInt())).thenReturn(Optional.of(project));
		when(employeeMasterRepository.findAllById(any())).thenReturn(employeeMasters);

		ResponseEntity<EnumMap<ApiResponse, Object>> response = projectTeamServiceImpl
				.getProjectManagerListByProjectId(1, 1, 1);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		EnumMap<ApiResponse, Object> resultMap = response.getBody();
		assertTrue((Boolean) resultMap.get(ApiResponse.SUCCESS));
		assertEquals(2, ((List<?>) resultMap.get(ApiResponse.DATA)).size());
	}

	@Test
	void testCustomerByCustomerId() {
		assertNotNull(projectTeamServiceImpl.getProjectTeamById(anyInt()));
		when(projectTeamRepository.findById(anyInt())).thenReturn(null);
		assertThrows(CRMException.class, () -> {
			projectTeamServiceImpl.getProjectTeamById(anyInt());
		});
	}

}
