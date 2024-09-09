package ai.rnt.customerFeedback.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VendorKYCDtoForm {

	private Integer vendorKycId;

	private String contactName;

	private String emailId;

	private String mobileNo;

	private String leagalName;
}
