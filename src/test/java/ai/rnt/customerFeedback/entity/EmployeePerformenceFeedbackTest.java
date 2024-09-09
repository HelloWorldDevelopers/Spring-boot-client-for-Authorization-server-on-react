package ai.rnt.customerFeedback.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class EmployeePerformenceFeedbackTest {

	@Test
	void testEmployeePerformenceFeedbackEntity() {
		EmployeePerformenceFeedback feedback = new EmployeePerformenceFeedback();
		feedback.setEmpPerfFeedbackId(1);
		QuestionMaster questionMaster = new QuestionMaster();
		questionMaster.setQuestionId(1);
		feedback.setQuestionMaster(questionMaster);
		feedback.setAnswer("Good");

		EmployeePerformenceMaster employeePerformenceMaster = new EmployeePerformenceMaster();
		employeePerformenceMaster.setEmpPerformenceId(1);

		EmployeeMaster employeeMaster = new EmployeeMaster();
		employeeMaster.setStaffId(1);

		Project project = new Project();
		project.setProjectId(1);

		feedback.setEmployeePerformenceMaster(employeePerformenceMaster);

		assertEquals(1, feedback.getEmpPerfFeedbackId());
		assertEquals(questionMaster, feedback.getQuestionMaster());
		assertEquals("Good", feedback.getAnswer());
		assertEquals(employeePerformenceMaster, feedback.getEmployeePerformenceMaster());
		assertEquals(1, feedback.getEmployeePerformenceMaster().getEmpPerformenceId());

	}

}
