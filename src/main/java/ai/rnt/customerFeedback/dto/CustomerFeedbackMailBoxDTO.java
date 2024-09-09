package ai.rnt.customerFeedback.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerFeedbackMailBoxDTO {
	private Integer mailboxId;

	private CustomerFeedbackMasterDto customerFeedbackMaster;

	private String fromEmail;

	private String toEmail;

	private String subject;

	private String emailBody;
}
