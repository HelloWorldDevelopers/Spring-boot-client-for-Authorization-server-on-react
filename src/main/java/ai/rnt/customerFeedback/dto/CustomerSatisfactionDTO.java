package ai.rnt.customerFeedback.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerSatisfactionDTO {
	private Integer custSatisfactionId;
	
	private MailBoxDTO customerSatisfactionMailBox;

	private Integer addressId;
	
	private Integer customerId;
	
	private String companyName;
	
	private String contactPersonName;
	
	private String contactPersonEmail;
	
	private String contactPersonNo;
	
	private Boolean filled;
	
	private String filledDate;
	
	private String formCreatedDate;
	
	private Integer sortStatus;
	
	private Boolean isEmailSend;

}
