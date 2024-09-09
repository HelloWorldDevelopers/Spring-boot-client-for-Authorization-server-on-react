package ai.rnt.customerFeedback.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailBoxDTO {

	private Integer mailboxId; 

	private String fromEmail;
	
	private String toEmail;
	
	private String subject;
	
	private String emailBody;
}
