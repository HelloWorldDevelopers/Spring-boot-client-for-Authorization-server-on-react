package ai.rnt.customerFeedback.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerFeedbackMasterDTONew {
	private Integer custFeedbackId;

	private String role;

	private String feedbackBy;
	
	private String emailId;

	private ProjectDTOnew project;
	
	private Boolean filled;

	private String filledDate;

}
