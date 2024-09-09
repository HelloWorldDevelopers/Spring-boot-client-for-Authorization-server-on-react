package ai.rnt.customerFeedback.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EmployeePerformenceMailBoxDTOTest {

	@Test
	void test() {
		EmployeePerformenceMasterNewDTO customerfeedback = new EmployeePerformenceMasterNewDTO();
		customerfeedback.setEmpPerformenceId(1);
		customerfeedback.setProjectTeamId(1);
		EmployeePerformenceMailBoxDTO customerFeedbackMailBoxDTO = new EmployeePerformenceMailBoxDTO();
		customerFeedbackMailBoxDTO.setMailboxId(1);
		customerFeedbackMailBoxDTO.setEmployeePerformenceMaster(customerfeedback);
		customerFeedbackMailBoxDTO.setEmailBody("email boddy");
		customerFeedbackMailBoxDTO.setToEmail("n.raut@rnt.si");
		customerFeedbackMailBoxDTO.setSubject("email body");
		customerFeedbackMailBoxDTO.setFromEmail("untrntapps@rabbitandtortortiose");
		assertEquals(1, customerFeedbackMailBoxDTO.getMailboxId());
		assertEquals("email boddy", customerFeedbackMailBoxDTO.getEmailBody());
		assertEquals("n.raut@rnt.si", customerFeedbackMailBoxDTO.getToEmail());
		assertEquals("email body", customerFeedbackMailBoxDTO.getSubject());
		assertEquals("untrntapps@rabbitandtortortiose", customerFeedbackMailBoxDTO.getFromEmail());
		assertEquals(customerfeedback, customerFeedbackMailBoxDTO.getEmployeePerformenceMaster());
	}

}
