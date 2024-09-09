package ai.rnt.customerFeedback.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendorServiceProviderDTO {

	private Integer svcsProviderId;
	
	private String descOfServices;

	private String serviceAccgCode;

	private String gstRateExpected;

	private String isCompSchemeUnderGst;
}
