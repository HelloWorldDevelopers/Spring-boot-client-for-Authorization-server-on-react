package ai.rnt.customerFeedback.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerSatisfactionMailBoxDTO {
	private Integer mailboxId;

	private CustomerSatisfactionMasterDto customerSatisfactionMaster;

	private String fromEmail;
	
	private String toEmail;
	
	private String subject;
	
	private String emailBody;
}
