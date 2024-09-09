package ai.rnt.customerFeedback.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LeadReportingManagerDTOTest {

	@Test
	void test() {
		ProjectDTOnew projectDTOnew = new ProjectDTOnew();
		projectDTOnew.setProjectId(1);
		projectDTOnew.setProjectName("project Name");
		projectDTOnew.setCustomer(new CustomerDto());
		EmployeeMasterDTO employeeMasterDTO = new EmployeeMasterDTO();
		employeeMasterDTO.setEmailId("emailId");
		employeeMasterDTO.setStaffId(1234);
		employeeMasterDTO.setUserId("NR1613");
		employeeMasterDTO.setFirstName("firstname");
		employeeMasterDTO.setLastName("sdc");
		LeadReportingManagerDTO leadReportingManagerDTO = new LeadReportingManagerDTO();
		leadReportingManagerDTO.setProjectTeamId(1);
		leadReportingManagerDTO.setProject(projectDTOnew);
		leadReportingManagerDTO.setReportLeadName("pqr");
		leadReportingManagerDTO.setReportLeadEmail("nish@rnt.ai");
		leadReportingManagerDTO.setReportLeadContactNo("7656789876");
		assertEquals(1, leadReportingManagerDTO.getProjectTeamId());
		assertEquals(projectDTOnew, leadReportingManagerDTO.getProject());
		assertEquals("pqr", leadReportingManagerDTO.getReportLeadName());
		assertEquals("nish@rnt.ai", leadReportingManagerDTO.getReportLeadEmail());
		assertEquals("7656789876", leadReportingManagerDTO.getReportLeadContactNo());

	}

}
