package ai.rnt.customerFeedback.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CustomerFeedbackMasterDto {

	private Integer custFeedbackId;

	private String role;

	private String feedbackBy;
	
	private String emailId;

	private ProjectDto projectDto;

}
