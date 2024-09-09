package ai.rnt.customerFeedback.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeadReportingManagerDTO {
	
	private Integer projectTeamId;
	
	private ProjectDTOnew project;

//	private EmployeeMasterDTO employeeMastar;

	private String reportLeadName;

	private String reportLeadEmail;

	private String reportLeadContactNo;
	
}
