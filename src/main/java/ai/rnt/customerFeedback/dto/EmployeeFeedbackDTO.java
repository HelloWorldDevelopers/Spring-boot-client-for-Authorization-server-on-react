package ai.rnt.customerFeedback.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeFeedbackDTO {
	private Integer empPerfFeedbackId;
	private EmployeePerformenceMasterNewDTO employeePerformenceMaster;
	private String answer;
	private QuestionDTO questionMaster;
}
