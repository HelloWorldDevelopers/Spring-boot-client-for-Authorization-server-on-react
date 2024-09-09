package ai.rnt.customerFeedback.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectDTOnew {
	private Integer projectId;
	private String projectName;
	private String projectAlias;
	private CustomerDto customer;
}
