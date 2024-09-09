package ai.rnt.customerFeedback.dto;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectDto {

	private Integer projectId;
	private String projectName;
	private String projectAlias;
	private CustomerDto customer;
	
	private Set<EmployeeMasterDTO> employeeMaster = new HashSet<>();

}
