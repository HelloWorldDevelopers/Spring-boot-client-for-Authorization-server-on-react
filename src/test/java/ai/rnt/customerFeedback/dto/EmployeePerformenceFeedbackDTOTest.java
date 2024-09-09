package ai.rnt.customerFeedback.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EmployeePerformenceFeedbackDTOTest {

	@Test
	void testGetterAndSetter() {
		EmployeePerformenceFeedbackDTO dto = new EmployeePerformenceFeedbackDTO();
		dto.setEmpPerfFeedbackId(1);
		EmployeePerformenceMasterDTO employeePerformenceMasterDTO = new EmployeePerformenceMasterDTO();
		dto.setEmployeePerformenceMaster(employeePerformenceMasterDTO);
		QuestionMasterDto questionMasterDto = new QuestionMasterDto();
		dto.setQuestionMaster(questionMasterDto);
		dto.setAnswer("Excellent");
		assertEquals(1, dto.getEmpPerfFeedbackId());
		assertEquals(employeePerformenceMasterDTO, dto.getEmployeePerformenceMaster());
		assertEquals(questionMasterDto, dto.getQuestionMaster());
		assertEquals("Excellent", dto.getAnswer());
	}

}
