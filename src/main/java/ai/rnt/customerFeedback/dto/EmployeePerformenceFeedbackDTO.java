package ai.rnt.customerFeedback.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeePerformenceFeedbackDTO {
	private Integer empPerfFeedbackId;
	private EmployeePerformenceMasterDTO employeePerformenceMaster;
	private QuestionMasterDto questionMaster;

	private String answer;

}
