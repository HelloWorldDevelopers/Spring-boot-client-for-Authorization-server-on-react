package ai.rnt.customerFeedback.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ai.rnt.customerFeedback.dto.CustomerDto;
import ai.rnt.customerFeedback.dto.EmployeeMasterDTO;
import ai.rnt.customerFeedback.dto.EmployeePerformenceFeedbackDTO;
import ai.rnt.customerFeedback.dto.EmployeePerformenceMasterDTO;
import ai.rnt.customerFeedback.dto.LeadReportingManagerDTO;
import ai.rnt.customerFeedback.dto.McqOptionsDto;
import ai.rnt.customerFeedback.dto.ProjectDTOnew;
import ai.rnt.customerFeedback.dto.QuestionMasterDto;
import ai.rnt.customerFeedback.entity.Customer;
import ai.rnt.customerFeedback.entity.EmployeeMaster;
import ai.rnt.customerFeedback.entity.EmployeePerformenceFeedback;
import ai.rnt.customerFeedback.entity.EmployeePerformenceMaster;
import ai.rnt.customerFeedback.entity.McqOptions;
import ai.rnt.customerFeedback.entity.Project;
import ai.rnt.customerFeedback.entity.QuestionMaster;
import ai.rnt.customerFeedback.exception.CRMException;
import ai.rnt.customerFeedback.repository.EmployeePerformenceFeedbackRepository;
import ai.rnt.customerFeedback.repository.EmployeePerformenceMasterRepository;
import ai.rnt.customerFeedback.repository.QuestionRepository;

@ExtendWith(MockitoExtension.class)
class EmployeePerformenceFeedbackServiceImplTest {

	@Mock
	EmployeePerformenceMasterRepository employeePerformenceMasterRepository;

	@Mock
	EmployeePerformenceFeedbackRepository employeePerformenceFeedbackRepository;

	@Mock
	QuestionRepository questionRepository;

	@InjectMocks
	EmployeePerformenceFeedbackServiceImpl employeePerformenceFeedbackServiceImpl;

	@Test
	void testsaveEmployeePerformenceFeedback() {
		List<EmployeePerformenceFeedbackDTO> empFeedDTOList = new ArrayList<>();
		EmployeePerformenceFeedbackDTO empFeedDTO = new EmployeePerformenceFeedbackDTO();
		EmployeePerformenceMasterDTO empPerMasterDTO = new EmployeePerformenceMasterDTO();
		LeadReportingManagerDTO leadReportingDTO = new LeadReportingManagerDTO();
		leadReportingDTO.setProjectTeamId(1);
		leadReportingDTO.setReportLeadEmail("emai.com");
		leadReportingDTO.setReportLeadName("nijdcw");
		leadReportingDTO.setReportLeadContactNo("789643245");
		EmployeeMasterDTO employeeMasterDTO = new EmployeeMasterDTO();
		employeeMasterDTO.setStaffId(1);
		employeeMasterDTO.setEmailId("emial@gmail.com");
		employeeMasterDTO.setUserId("US1123");
		CustomerDto customerDto = new CustomerDto();
		customerDto.setCompanyName("comapny");
		customerDto.setCustomerId(1);
		customerDto.setEmailId("email@gmail.com");
		ProjectDTOnew projectDTO = new ProjectDTOnew();
		projectDTO.setProjectId(1);
		projectDTO.setProjectName("projectName");
		projectDTO.setCustomer(customerDto);
		empPerMasterDTO.setEmpPerformenceId(1);
		LocalDateTime fixedDateTime = LocalDateTime.of(2024, 4, 19, 12, 0);
		empPerMasterDTO.setCreatedDate(fixedDateTime);
		empPerMasterDTO.setFilled(true);
		empPerMasterDTO.setFilledDate("22-03-2024");
		QuestionMasterDto questionMasteDTO = new QuestionMasterDto();
		questionMasteDTO.setFormType(3);
		questionMasteDTO.setIsMcqType(1);
		questionMasteDTO.setQuestion("question?");
		questionMasteDTO.setQuestionId(1);
		List<McqOptionsDto> mcqList = new ArrayList<>();
		McqOptionsDto mcqOptionsDto = new McqOptionsDto();
		mcqOptionsDto.setMcqOptionId(1);
		mcqOptionsDto.setOption("four");
		mcqList.add(mcqOptionsDto);
		questionMasteDTO.setMcqOptionsDtos(mcqList);
		empFeedDTO.setAnswer("nsdxj");
		empFeedDTO.setEmployeePerformenceMaster(empPerMasterDTO);
		empFeedDTO.setQuestionMaster(questionMasteDTO);
		empFeedDTO.setEmpPerfFeedbackId(1);
		empFeedDTOList.add(empFeedDTO);
		EmployeePerformenceMaster employeePerformenceMaster = new EmployeePerformenceMaster();
		Project projectEntity = new Project();
		projectEntity.setProjectId(1);
		projectEntity.setProjectName("name");
		projectEntity.setCustomer(new Customer());
		EmployeeMaster employee = new EmployeeMaster();
		employee.setStaffId(1);
		employee.setEmailId("email@gmail.com");
		employee.setEmployeeJobTitle("jottitle");
		employeePerformenceMaster.setEmpPerformenceId(1);
		employeePerformenceMaster.setFilled(false);
		employeePerformenceMaster.setFilledDate(null);
		employeePerformenceMaster.setCreatedDate(fixedDateTime);
		QuestionMaster questionMaster = new QuestionMaster();
		questionMaster.setFormType(1);
		questionMaster.setIsMcqType(1);
		questionMaster.setQuestionId(1);
		questionMaster.setQuestion("question");
		List<McqOptions> mcqlist = new ArrayList<>();
		McqOptions mcqoptions = new McqOptions();
		mcqoptions.setMcqOptionId(1);
		mcqoptions.setOption("disagree");
		mcqlist.add(mcqoptions);
		questionMaster.setMcqOptions(mcqlist);
		List<EmployeePerformenceFeedback> list = new ArrayList<>();
		EmployeePerformenceFeedback employeePerformenceFeedback = new EmployeePerformenceFeedback();
		employeePerformenceFeedback.setAnswer("answer");
		employeePerformenceFeedback.setEmployeePerformenceMaster(employeePerformenceMaster);
		employeePerformenceFeedback.setEmpPerfFeedbackId(1);
		employeePerformenceFeedback.setQuestionMaster(questionMaster);
		when(employeePerformenceFeedbackRepository.findByEmployeePerformenceMasterEmpPerformenceId(anyInt()))
				.thenReturn(list);
		when(employeePerformenceMasterRepository.findById(anyInt())).thenReturn(Optional.of(employeePerformenceMaster));
		try {

			employeePerformenceFeedbackServiceImpl.saveEmployeePerformenceFeedback(empFeedDTOList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertThrows(CRMException.class,
				() -> employeePerformenceFeedbackServiceImpl.saveEmployeePerformenceFeedback(empFeedDTOList));
	}

	@Test
	void testgetAllEmployeePerformenceFeedback() {
		List<EmployeePerformenceFeedback> empFeedList = new ArrayList<>();
		EmployeePerformenceFeedback empl = new EmployeePerformenceFeedback();
		empFeedList.add(empl);
		when(employeePerformenceFeedbackRepository.findAll()).thenReturn(empFeedList);
		assertNotNull(employeePerformenceFeedbackServiceImpl.getAllEmployeePerformenceFeedback());
		when(employeePerformenceFeedbackRepository.findAll()).thenThrow(NullPointerException.class);
		assertThrows(CRMException.class,
				() -> employeePerformenceFeedbackServiceImpl.getAllEmployeePerformenceFeedback());
	}

	@Test
	void testgetEmployeePerformenceFeedbackById() {
		int customerId = 1;
		List<EmployeePerformenceFeedback> customerFeedbackList = new ArrayList<>();

		when(employeePerformenceFeedbackRepository
				.findByEmployeePerformenceMasterEmpPerformenceIdOrderByQuestionMasterQuestionIdAsc(customerId))
				.thenReturn(customerFeedbackList);

		assertNotNull(employeePerformenceFeedbackServiceImpl.getEmployeePerformenceFeedbackById(customerId));
		when(employeePerformenceFeedbackRepository
				.findByEmployeePerformenceMasterEmpPerformenceIdOrderByQuestionMasterQuestionIdAsc(customerId))
				.thenThrow(CRMException.class);
		assertThrows(CRMException.class,
				() -> employeePerformenceFeedbackServiceImpl.getEmployeePerformenceFeedbackById(customerId));
	}

}
