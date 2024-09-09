package ai.rnt.customerFeedback.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class VendorRegistrationDto {

	private Integer vendorKycId;

	private MailBoxDTO vendorOnboardingMailBox;
	
	private String contactName;
	
	//private String leagalName;
	private String tradeName;

	private String emailId;

	private String mobileNo;
	
	private boolean status;
	
	private LocalDateTime createdDate;
	
	private String parseDate;
	
	private String filledDate;
	
	private boolean formFilled;
	
	private Integer sortStatus;
	
	private Boolean isEmailSend;

	

}
