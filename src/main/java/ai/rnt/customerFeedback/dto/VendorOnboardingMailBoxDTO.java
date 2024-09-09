/**
 * 
 */
package ai.rnt.customerFeedback.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendorOnboardingMailBoxDTO {
	
	private Integer mailboxId; 

	private VendorKYCNewDTO vendorKYC;
	
	private String fromEmail;
	
	private String toEmail;
	
	private String subject;
	
	private String emailBody;

}
