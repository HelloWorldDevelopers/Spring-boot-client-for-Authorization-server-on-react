package ai.rnt.customerFeedback.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerSatisfactionListDto {
	private Integer custSatisfactionId;

	private String createdDate;

	private String companyName;

	private Integer customerId;

	private String filledDate;

	private Boolean filled;

	private String emailId;

	private String customerContactNumber;

	private String contactPersonName;

}
