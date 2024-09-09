package ai.rnt.customerFeedback.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EmployeeFeedbackDTOTest {

	@Test
	void test() {
		EmployeeFeedbackDTO employeeFeedbackDTO = new EmployeeFeedbackDTO();
		QuestionDTO questionDTO = new QuestionDTO();
		EmployeePerformenceMasterNewDTO employeemaster = new EmployeePerformenceMasterNewDTO();
		employeeFeedbackDTO.setAnswer("agree");
		employeeFeedbackDTO.setEmpPerfFeedbackId(1);
		employeeFeedbackDTO.setQuestionMaster(questionDTO);
		employeeFeedbackDTO.setEmployeePerformenceMaster(employeemaster);

		assertEquals(1, employeeFeedbackDTO.getEmpPerfFeedbackId());
		assertEquals("agree", employeeFeedbackDTO.getAnswer());
		assertEquals(questionDTO, employeeFeedbackDTO.getQuestionMaster());
		assertEquals(employeemaster, employeeFeedbackDTO.getEmployeePerformenceMaster());

	}

}
