package ai.rnt.customerFeedback.service.impl;

import static ai.rnt.customerFeedback.constants.SecurityConstant.EMPLOYEEPERFORMENCEID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ai.rnt.customerFeedback.dto.EmployeeMasterDTO;
import ai.rnt.customerFeedback.dto.EmployeePerformenceMailBoxDTO;
import ai.rnt.customerFeedback.dto.EmployeePerformenceMasterDTO;
import ai.rnt.customerFeedback.dto.EmployeePerformenceMasterNewDTO;
import ai.rnt.customerFeedback.dto.LeadReportingManagerDTO;
import ai.rnt.customerFeedback.dto.ProjectDTOEmployee;
import ai.rnt.customerFeedback.dto.ProjectDTOnew;
import ai.rnt.customerFeedback.entity.EmployeePerformenceFeedback;
import ai.rnt.customerFeedback.entity.EmployeePerformenceMailBox;
import ai.rnt.customerFeedback.entity.EmployeePerformenceMaster;
import ai.rnt.customerFeedback.entity.Project;
import ai.rnt.customerFeedback.entity.ProjectTeam;
import ai.rnt.customerFeedback.enums.ApiResponse;
import ai.rnt.customerFeedback.exception.CRMException;
import ai.rnt.customerFeedback.repository.EmployeeMasterRepository;
import ai.rnt.customerFeedback.repository.EmployeePerformenceFeedbackRepository;
import ai.rnt.customerFeedback.repository.EmployeePerformenceMailBoxRepo;
import ai.rnt.customerFeedback.repository.EmployeePerformenceMasterRepository;
import ai.rnt.customerFeedback.repository.ProjectRepository;
import ai.rnt.customerFeedback.repository.ProjectTeamRepository;
import ai.rnt.customerFeedback.repository.RoleMasterRepository;

@ExtendWith(MockitoExtension.class)
class EmployeePerformenceMasterServiceImplTest {

	@InjectMocks
	EmployeePerformenceMasterServiceImpl employeePerformenceMasterService;
	@Mock
	EmployeePerformenceMasterRepository employeePerformenceMasterRepository;

	@Mock
	EmployeePerformenceFeedbackRepository employeePerformenceFeedbackRepository;

	@Mock
	EmployeeMasterRepository employeeMasterRepository;

	@Mock
	RoleMasterRepository roleMasterRepository;

	@Mock
	ProjectTeamRepository projectTeamRepository;

	@Mock
	ProjectRepository projectRepository;

	@Mock
	private EmployeePerformenceMailBoxRepo performenceMailBoxRepo;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testSaveEmployeePerformenceMaster_Success() {
		// Mock data
		EmployeePerformenceMasterDTO employeePerformenceDTO = new EmployeePerformenceMasterDTO();
		employeePerformenceDTO.setEmpPerformenceId(1);
		employeePerformenceDTO.setCreatedDate(LocalDateTime.now());
		EmployeeMasterDTO empDTO = new EmployeeMasterDTO();
		ProjectDTOnew projectDTO = new ProjectDTOnew();
		projectDTO.setProjectId(1);
		LeadReportingManagerDTO leadReportingDTO = new LeadReportingManagerDTO();
		leadReportingDTO.setProjectTeamId(1);
		leadReportingDTO.setReportLeadName("wdcwc");
		leadReportingDTO.setReportLeadEmail("wdcwdc");
		leadReportingDTO.setReportLeadContactNo("788654");
		empDTO.setStaffId(1566);
		assertNotNull(employeePerformenceMasterService.saveEmployeePerformenceMaster(employeePerformenceDTO));
	}

	@Test
	void testSaveEmployeePerformenceMaster_Exception() {
		EmployeePerformenceMasterDTO mockDTO = new EmployeePerformenceMasterDTO();
		mockDTO.setCreatedDate(LocalDateTime.now());

		when(employeePerformenceMasterRepository.save(any(EmployeePerformenceMaster.class)))
				.thenThrow(RuntimeException.class);

		assertThrows(CRMException.class, () -> employeePerformenceMasterService.saveEmployeePerformenceMaster(mockDTO));
	}

	@Test
	void testGetAllEmployeesPerformence_Success2() {
		List<EmployeePerformenceMaster> emplperformenceList = new ArrayList<>();
		EmployeePerformenceMaster emp = new EmployeePerformenceMaster();
		emp.setEmpPerformenceId(1);
		emp.setCreatedDate(LocalDateTime.now());
		emplperformenceList.add(emp);

		when(employeePerformenceMasterRepository.findAll(Sort.by(Sort.Direction.DESC, EMPLOYEEPERFORMENCEID)))
				.thenReturn(emplperformenceList);
		List<EmployeePerformenceFeedback> feedbacklist = new ArrayList<>();
		EmployeePerformenceFeedback feeback = new EmployeePerformenceFeedback();
		feeback.setCreatedDate(LocalDateTime.now());
		feeback.setAnswer("ans");
		feeback.setEmpPerfFeedbackId(1);
		feedbacklist.add(feeback);

		when(employeePerformenceFeedbackRepository.findByEmployeePerformenceMasterEmpPerformenceId(1))
				.thenReturn(feedbacklist);

		assertNotNull(employeePerformenceMasterService.getAllEmployeesPerformence());
		when(employeePerformenceMasterRepository.findAll(Sort.by(Sort.Direction.DESC, EMPLOYEEPERFORMENCEID)))
				.thenThrow(CRMException.class);
		assertThrows(CRMException.class, () -> employeePerformenceMasterService.getAllEmployeesPerformence());
	}

	@Test
	void testGetEmployeePerformenceById_Success() {
		int id = 1;
		EmployeePerformenceMaster employeePerformenceMaster = new EmployeePerformenceMaster(); // Create your instance
		employeePerformenceMaster.setCreatedDate(LocalDateTime.now());
		employeePerformenceMaster.setEmpPerformenceId(1);
		employeePerformenceMaster.setFilled(true);
		ProjectTeam projectTeam = new ProjectTeam();
		Project project = new Project();
		project.setProjectId(1);
		project.setProjectEndDate(LocalDate.now());
		projectTeam.setProject(project);
		projectTeam.setProjectTeamId(1);
		projectTeam.setReportLeadContactNo("7887654456");
		projectTeam.setReportLeadEmail("email.com");
		projectTeam.setReportLeadName("sdc iwdc");
		when(employeePerformenceMasterRepository.findById(id)).thenReturn(Optional.of(employeePerformenceMaster));
		when(employeePerformenceFeedbackRepository.findByEmployeePerformenceMasterEmpPerformenceId(anyInt()))
				.thenReturn(new ArrayList<>());
		ResponseEntity<EnumMap<ApiResponse, Object>> responseEntity = employeePerformenceMasterService
				.getEmployeePerformenceById(id);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(true, responseEntity.getBody().get(ApiResponse.SUCCESS));
		assertNotNull(responseEntity.getBody().get(ApiResponse.DATA));
	}

	@Test
	void testGetEmployeePerformenceById_NotFound() {
		int id = 1;

		when(employeePerformenceMasterRepository.findById(id)).thenReturn(Optional.empty());

		assertThrows(CRMException.class, () -> employeePerformenceMasterService.getEmployeePerformenceById(id));
	}

	@Test
	void testUpdateEmployeePerformenceMaster_Success() {
		int id = 1;
		EmployeePerformenceMasterDTO employeePerformenceDTO = new EmployeePerformenceMasterDTO();
		employeePerformenceDTO.setEmpPerformenceId(1);
		EmployeeMasterDTO empDTO = new EmployeeMasterDTO();
		ProjectDTOnew projectDTO = new ProjectDTOnew();
		projectDTO.setProjectId(1);

		ProjectDTOEmployee projectDTOEmployee = new ProjectDTOEmployee();
		employeePerformenceDTO.setProject(projectDTOEmployee);
		employeePerformenceDTO.setEmployeeMaster(empDTO);
		employeePerformenceDTO.setProjectManagerEmail("abc@gmail.com");
		employeePerformenceDTO.setProjectManagerName("Toylor");
		empDTO.setStaffId(1566);
		LeadReportingManagerDTO leadReportingManagerDTO = new LeadReportingManagerDTO();
		leadReportingManagerDTO.setReportLeadName("njdycwcd");
		leadReportingManagerDTO.setReportLeadContactNo("9876544333");
		leadReportingManagerDTO.setReportLeadEmail("email.com");
		leadReportingManagerDTO.setProjectTeamId(1);
		EmployeePerformenceMaster employeePerformenceMaster = new EmployeePerformenceMaster();
		when(employeePerformenceMasterRepository.findById(id)).thenReturn(Optional.of(employeePerformenceMaster));
		assertNotNull(employeePerformenceMasterService.updateEmployeePerformenceMaster(id, employeePerformenceDTO));

	}

	@Test
	void testSaveEmployeePerformanceMail_Success() {
		EmployeePerformenceMailBoxDTO performenceMailBoxDTO = new EmployeePerformenceMailBoxDTO();
		EmployeePerformenceMasterNewDTO masterNewDTO = new EmployeePerformenceMasterNewDTO();
		masterNewDTO.setEmpPerformenceId(1);
		masterNewDTO.setProjectTeamId(1);
		masterNewDTO.setFilled(true);
		performenceMailBoxDTO.setEmployeePerformenceMaster(masterNewDTO);
		performenceMailBoxDTO.setMailboxId(1);
		EmployeePerformenceMailBox performenceMailBox = new EmployeePerformenceMailBox();
		performenceMailBox.setMailboxId(1);
		EmployeePerformenceMaster employeePerformenceMaster = new EmployeePerformenceMaster();
		employeePerformenceMaster.setEmpPerformenceId(1);
		performenceMailBox.setEmployeePerformenceMaster(employeePerformenceMaster);
		when(performenceMailBoxRepo
				.findByEmployeePerformance(performenceMailBoxDTO.getEmployeePerformenceMaster().getEmpPerformenceId()))
				.thenReturn(performenceMailBox);

		try {
			ResponseEntity<EnumMap<ApiResponse, Object>> responseEntity = employeePerformenceMasterService
					.saveEmployeePerformanceMail(performenceMailBoxDTO);
			assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
			assertNotNull(responseEntity.getBody().get(ApiResponse.DATA));

		} catch (Exception e) {

		}

	}

	@Test
	void testgetSavedEmployeePerformanceMail() {
		EmployeePerformenceMaster employeePerformence = new EmployeePerformenceMaster();
		employeePerformence.setEmpPerformenceId(1);
		employeePerformence.setProjectManagerName("wiudwoiu");
		EmployeePerformenceMailBox vendorOnboardingMailBox = new EmployeePerformenceMailBox();
		vendorOnboardingMailBox.setMailboxId(1);
		vendorOnboardingMailBox.setEmailBody("mail body");
		vendorOnboardingMailBox.setEmployeePerformenceMaster(employeePerformence);
		when(performenceMailBoxRepo.findByEmployeePerformance(1)).thenReturn(vendorOnboardingMailBox);
		ResponseEntity<EnumMap<ApiResponse, Object>> responseEntity = employeePerformenceMasterService
				.getSavedEmployeePerformanceMail(1);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertNotNull(responseEntity.getBody().get(ApiResponse.DATA));

	}

}
