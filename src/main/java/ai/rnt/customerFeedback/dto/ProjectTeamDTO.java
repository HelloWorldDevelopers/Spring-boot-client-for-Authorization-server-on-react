package ai.rnt.customerFeedback.dto;

import ai.rnt.customerFeedback.entity.EmployeeMaster;
import ai.rnt.customerFeedback.entity.Project;
import ai.rnt.customerFeedback.entity.RoleMaster;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectTeamDTO {
	private Integer projectTeamId;

	private Project project;

	private EmployeeMaster employeeMastar;

	private RoleMaster roleMaster;
	
	

}
