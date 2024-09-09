package ai.rnt.customerFeedback.dto;

import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VenderBankDetailsDto {
	
	private Integer vendorBankDtlId;

	private String bankName;

	private String branch;

	private String beneficiaryAccName;
	
	@Pattern(regexp = "^[0-9]*$", message = "Enter only digits")
	private String accountNo;

	private CountryMasterDTO countryMaster;

	private String bankAddressLine1;

	private String bankAddressLine2;

	private String city;

	private String state;

	private String zipCode;

	private String intermediaryBankDtl;

	private String accCurrency;

	private String ifscCode;

	private String swiftCode;
	
	private String companyTurnover;
	
	private String isItrFiled;
	
}
