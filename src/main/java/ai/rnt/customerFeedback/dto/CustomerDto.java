package ai.rnt.customerFeedback.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CustomerDto {
	private Integer customerId;
	private String companyName;
	private String emailId;
	private String customerContactNumber;
	private String contactPersonName;

}

