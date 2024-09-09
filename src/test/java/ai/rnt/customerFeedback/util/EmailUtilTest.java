package ai.rnt.customerFeedback.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.EnumMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ai.rnt.customerFeedback.entity.AddressMaster;
import ai.rnt.customerFeedback.entity.CustomerFeedbackMailBox;
import ai.rnt.customerFeedback.entity.CustomerSatisfactionMailBox;
import ai.rnt.customerFeedback.entity.CustomerSatisfactionMaster;
import ai.rnt.customerFeedback.entity.EmployeeMaster;
import ai.rnt.customerFeedback.entity.EmployeePerformenceMailBox;
import ai.rnt.customerFeedback.entity.EmployeePerformenceMaster;
import ai.rnt.customerFeedback.entity.Project;
import ai.rnt.customerFeedback.entity.VendorKYC;
import ai.rnt.customerFeedback.entity.VendorOnboardingMailBox;
import ai.rnt.customerFeedback.enums.ApiResponse;
import ai.rnt.customerFeedback.repository.AddressMasterRepository;
import ai.rnt.customerFeedback.repository.CustomerFeedbackMailBoxRepo;
import ai.rnt.customerFeedback.repository.CustomerFeedbackMasterRepository;
import ai.rnt.customerFeedback.repository.CustomerSatisfactionMailBoxRepo;
import ai.rnt.customerFeedback.repository.CustomerSatisfactionMasterRepository;
import ai.rnt.customerFeedback.repository.EmployeeMasterRepository;
import ai.rnt.customerFeedback.repository.EmployeePerformenceMailBoxRepo;
import ai.rnt.customerFeedback.repository.EmployeePerformenceMasterRepository;
import ai.rnt.customerFeedback.repository.ProjectTeamRepository;
import ai.rnt.customerFeedback.repository.VendorKYCRepository;
import ai.rnt.customerFeedback.repository.VendorOnboardingMailBoxRepo;

@ExtendWith(MockitoExtension.class)
class EmailUtilTest {

	@Mock
	private EmployeeMasterRepository employeeMasterRepository;

	@Mock
	private CustomerSatisfactionMasterRepository customerSatisfactionMasterRepository;
	@Mock
	private CustomerFeedbackMailBoxRepo customerFeedbackMailBoxRepo;

	@InjectMocks
	private EmailUtil emailUtil;

	@Mock
	EmployeePerformenceMasterRepository employeePerformenceMasterRepository;

	@Mock
	AddressMasterRepository addressMasterRepository;

	@Mock
	VendorKYCRepository vendorKYCRepository;

	@Mock
	ProjectTeamRepository projectTeamRepository;

	@Mock
	CustomerFeedbackMasterRepository customerFeedbackMasterRepository;

	@Mock
	CustomerSatisfactionMailBoxRepo satisfactionMailBoxRepo;

	@Mock
	VendorOnboardingMailBoxRepo vendorOnboardingMailBoxRepo;

	@Mock
	EmployeePerformenceMailBoxRepo performenceMailBoxRepo;

	@Test
	void testSendEmail_Success() throws Exception {
		CustomerSatisfactionMaster mockCustomerSatisfactionMaster = new CustomerSatisfactionMaster();
		mockCustomerSatisfactionMaster.setCustSatisfactionId(1);
		mockCustomerSatisfactionMaster.setAddressId(1);
		AddressMaster mockAddressMaster = new AddressMaster();
		mockAddressMaster.setContactPersonEmail("test@gmail.com");
		mockAddressMaster.setContactPersonName("John Doe");
		CustomerSatisfactionMailBox satisfactionMailBox = new CustomerSatisfactionMailBox();
		satisfactionMailBox.setToEmail("test@gmail.com");
		satisfactionMailBox.setFromEmail("test1@gmail.com");
		satisfactionMailBox.setSubject("test demo");
		satisfactionMailBox.setEmailBody("test body");
		when(satisfactionMailBoxRepo.findByCustomerSatisfaction(anyInt())).thenReturn(satisfactionMailBox);
		ResponseEntity<EnumMap<ApiResponse, Object>> responseEntity = emailUtil.sendEmail(1);
		assertNotNull(responseEntity);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		EnumMap<ApiResponse, Object> resultMap = responseEntity.getBody();
		assertNotNull(resultMap);
	}

	@Test
	void testsendEmailVedor_Success() throws Exception {
		VendorKYC vendorKYC = new VendorKYC();
		vendorKYC.setEmailId("john.doe@gmail.com");
		VendorOnboardingMailBox vendorOnboardingMailBox = new VendorOnboardingMailBox();
		vendorOnboardingMailBox.setToEmail("test@gmail.com");
		vendorOnboardingMailBox.setFromEmail("test@gmail.com");
		vendorOnboardingMailBox.setSubject("test subject");
		vendorOnboardingMailBox.setEmailBody("test body");
		when(vendorOnboardingMailBoxRepo.findByVendoronboardingId(anyInt())).thenReturn(vendorOnboardingMailBox);
		ResponseEntity<EnumMap<ApiResponse, Object>> responseEntity = emailUtil.sendEmailVedor(123);

	}

	@Test
	void testsendEmailProjectEndFeedback() throws Exception {
		CustomerFeedbackMailBox customerFeedbackmailBox = new CustomerFeedbackMailBox();
		customerFeedbackmailBox.setMailboxId(1);
		customerFeedbackmailBox.setFromEmail("uatapps@rabbitandtortoise.com");
		customerFeedbackmailBox.setToEmail("necd@gmail.com");
		customerFeedbackmailBox.setEmailBody("i7eyrey 8erye eiuyieuc");
		customerFeedbackmailBox.setSubject("eiuui ieuy iuye ciueyc");
		when(customerFeedbackMailBoxRepo.findByCustomerFeedback(anyInt())).thenReturn(customerFeedbackmailBox);
		when(employeeMasterRepository.getEmailPassword()).thenReturn("senderPassword");
		ResponseEntity<EnumMap<ApiResponse, Object>> responseEntity = emailUtil.sendEmailProjectEndFeedback(123);

	}

	@Test
	void testsendEmailEmployeePerformence() throws Exception {
		EmployeePerformenceMaster employeePerformenceMaster = new EmployeePerformenceMaster();
		employeePerformenceMaster.setEmpPerformenceId(1);
		Project project = new Project();
		project.setProjectId(1);
		project.setProjectName("project");
		project.setProjectAlias("weiuyd");
		EmployeeMaster employee = new EmployeeMaster();
		employee.setStaffId(1);
		employee.setEmailId("wcwc@rnt.ai");
		employee.setLastName("nidc");
		employee.setFirstName("uefcd");
		employeePerformenceMaster.setEmployeeMaster(employee);
		employeePerformenceMaster.setProject(project);
		employeePerformenceMaster.setProjectManagerEmail("nikita@gmail.com");
		employeePerformenceMaster.setProjectManagerName("project Name");
		EmployeePerformenceMailBox performenceMailBox = new EmployeePerformenceMailBox();
		performenceMailBox.setToEmail("test@rnt.ai");
		performenceMailBox.setFromEmail("test1@rnt.ai");
		performenceMailBox.setSubject("test subject");
		performenceMailBox.setEmailBody("test body");
		when(performenceMailBoxRepo.findByEmployeePerformance(anyInt())).thenReturn(performenceMailBox);
		ResponseEntity<EnumMap<ApiResponse, Object>> responseEntity = emailUtil.sendEmailEmployeePerformence(1);
		assertNotNull(responseEntity);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		EnumMap<ApiResponse, Object> resultMap = responseEntity.getBody();
		assertNotNull(resultMap);

	}
}
