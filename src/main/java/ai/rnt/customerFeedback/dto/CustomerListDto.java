package ai.rnt.customerFeedback.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerListDto {
	private Integer custFeedbackId;
	
	private MailBoxDTO customerFeedbackMailBox;

	private String role;

	private String feedbackBy;
	
	private String emailId;

	private String createdDate;

	private String projectName;
	
	private String projectAlias;
	
	private Integer projectId;
	
	private String companyName;
	
	private Integer customerId;

	private Boolean filled;
	
	private String filledDate;
	
	private Integer sortStatus;
	
	private Boolean isEmailSend;

}
