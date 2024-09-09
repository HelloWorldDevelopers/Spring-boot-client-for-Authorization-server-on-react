package ai.rnt.customerFeedback.dto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class AddressMasterDTO {

	private Integer addressId;
	
	private Integer customerId;
	
	private String companyName;
	
	private CustomerDto customer;
	
	private String contactPersonName;
	
	private String contactPersonEmail;
	
	private String contactPersonNo;

}
