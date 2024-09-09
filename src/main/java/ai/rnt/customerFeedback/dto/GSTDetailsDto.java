package ai.rnt.customerFeedback.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class GSTDetailsDto {
	
	private Integer gstDtlId;

	private String gstNo;
	
	private String panNo;

	private VendorStateGSTCodeDTO vendorStateGSTCode;

	private String gstAddress;

	
}
