package ai.rnt.customerFeedback.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendorKYCDto {

	private Integer vendorKycId;

	private List<VendorDocumentAttachmentDto> vendorDocumentAttachmentList;

	private VenderBankDetailsDto venderBankDetails;

	private List<GSTDetailsDto> gstdetailsList;
	
	private List<VendorServiceProviderDTO> vendorServiceProviderList;
	
	private List<VendorMajorCustomerDTO> vendorMajorCustomerList;
	
	private List<VendorCoreGoodServicesDTO> vendorCoreGoodServicesList;
	
	private CountryMasterDTO billingcountryMaster;
	
	private CountryMasterDTO shippingcountryMaster;
	
	private String tradeName;

	private String leagalName;
	
    private String companyType;
    
    private String billingAddressLine1;
    
    private String billingAddressLine2;
	
	private String billingCity;
	
	private String billingState;
	
	private String billingZipCode;

	private String shippingAddressLine1;
	
	private String shippingAddressLine2;
	
	private String shippingCity;

	private String shippingState;

	private String shippingZipCode;
	
	private String alternateNo;

	private String contactPosition;

	private String contactName;
	
	private String emailId;
	
	private String mobileNo;
	
	private String website;
 
	private String telephoneNo;

	private String cinId;

	private String isUnderTaxExemptions;

	private String isUnderGst;

	private String declName;

	private String declDesignation;
	
	private LocalDate onboardingDate;
	
    private String spocName;

    private String spocTelNo;

    private String spocMobileNo;

    private String spocEmail;

    private String escalationName;

    private String escalationTel;

    private String escalationMobile;

    private String escalationEmail;

    private String salesName;

    private String salesTelNo;

    private String salesMobileNo;

    private String salesEmail;

    private String accountsName;

    private String accountsTelNo;

    private String accountsMobileNo;

    private String accountsEmail;

    private String isUnderMsme;
	
	private String parseDate;
	
	private String filledDate;
	
	private LocalDateTime createdDate;
	
	private boolean status;
	
	private boolean formFilled; 
	


}
