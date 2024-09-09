package ai.rnt.customerFeedback.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerSatisfactionMasterDTONew {
	
	private Integer custSatisfactionId;

	private Integer addressId;
	
	private AddressMasterDTO addressMaster;
	
	private Integer customerId;
	
	private String companyName;
	
//	private CustomerDto customer;
	
	private String contactPersonName;
	
	private String contactPersonEmail;
	
	private String contactPersonNo;
	
	private Boolean filled;
	
	private String filledDate;
	
	private String pseudoName;
}
