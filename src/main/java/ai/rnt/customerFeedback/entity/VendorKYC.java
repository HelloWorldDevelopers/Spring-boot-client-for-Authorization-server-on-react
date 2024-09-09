package ai.rnt.customerFeedback.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vendor_kyc")
@Setter
@Getter
@NoArgsConstructor
@Where(clause = "deleted_by is null")
public class VendorKYC extends Auditable {

	private static final long serialVersionUID = -2735963440340365177L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "kyc_id")
	private Integer vendorKycId;

	@OneToOne(mappedBy = "vendorKYC", fetch = FetchType.LAZY)
	private VendorOnboardingMailBox vendorOnboardingMailBox;

	@OneToOne(mappedBy = "vendorKYC", fetch = FetchType.LAZY)
	private VenderBankDetails venderBankDetails;

	@LazyCollection(LazyCollectionOption.TRUE)
	@OneToMany(mappedBy = "vendorKYC", fetch = FetchType.LAZY)
	private List<GSTDetails> gstdetailsList;

	@LazyCollection(LazyCollectionOption.TRUE)
	@OneToMany(mappedBy = "vendorKYC", fetch = FetchType.LAZY)
	private List<VendorServiceProvider> vendorServiceProviderList;

	@LazyCollection(LazyCollectionOption.TRUE)
	@OneToMany(mappedBy = "vendorKYC", fetch = FetchType.LAZY)
	private List<VendorMajorCustomer> vendorMajorCustomerList;

	@LazyCollection(LazyCollectionOption.TRUE)
	@OneToMany(mappedBy = "vendorKYC", fetch = FetchType.LAZY)
	private List<VendorCoreGoodServices> vendorCoreGoodServicesList;

	@LazyCollection(LazyCollectionOption.TRUE)
	@OneToMany(mappedBy = "vendorKYC", fetch = FetchType.LAZY)
	private List<VendorDocumentAttachment> vendorDocumentAttachmentList;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "billing_country_id")
	private CountryMaster billingcountryMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shipping_country_id")
	private CountryMaster shippingcountryMaster;

	@Column(name = "trade_name")
	private String tradeName;

	@Column(name = "legal_name")
	private String leagalName;

	@Column(name = "web_site")
	private String website;

	@Column(name = "billing_address_line1")
	private String billingAddressLine1;

	@Column(name = "billing_address_line2")
	private String billingAddressLine2;

	@Column(name = "billing_city")
	private String billingCity;

	@Column(name = "billing_state")
	private String billingState;

	@Column(name = "billing_zip_code")
	private String billingZipCode;

	@Column(name = "shipping_address_line1")
	private String shippingAddressLine1;

	@Column(name = "shipping_address_line2")
	private String shippingAddressLine2;

	@Column(name = "shipping_city")
	private String shippingCity;

	@Column(name = "shipping_state")
	private String shippingState;

	@Column(name = "shipping_zip_code")
	private String shippingZipCode;

	@Column(name = "telephone_no")
	private String telephoneNo;

	@Column(name = "cin_id")
	private String cinId;

	@Column(name = "contact_name")
	private String contactName;

	@Column(name = "contact_position")
	private String contactPosition;

	@Column(name = "email_id")
	private String emailId;

	@Column(name = "mobile_no")
	private String mobileNo;

	@Column(name = "alternate_no")
	private String alternateNo;

	@Column(name = "is_under_tax_exemptions")
	private String isUnderTaxExemptions;

	@Column(name = "is_under_gst")
	private String isUnderGst;

	@Column(name = "decl_name")
	private String declName;

	@Column(name = "decl_designation")
	private String declDesignation;

	@Column(name = "onboarding_date")
	private LocalDate onboardingDate;

	@Column(name = "spoc_name")
	private String spocName;

	@Column(name = "spoc_tel_no")
	private String spocTelNo;

	@Column(name = "spoc_mobile_no")
	private String spocMobileNo;

	@Column(name = "spoc_email")
	private String spocEmail;

	@Column(name = "escalation_name")
	private String escalationName;

	@Column(name = "escalation_tel")
	private String escalationTel;

	@Column(name = "escalation_mobile")
	private String escalationMobile;

	@Column(name = "escalation_email")
	private String escalationEmail;

	@Column(name = "sales_name")
	private String salesName;

	@Column(name = "sales_tel_no")
	private String salesTelNo;

	@Column(name = "sales_mobile_no")
	private String salesMobileNo;

	@Column(name = "sales_email")
	private String salesEmail;

	@Column(name = "accounts_name")
	private String accountsName;

	@Column(name = "accounts_tel_no")
	private String accountsTelNo;

	@Column(name = "accounts_mobile_no")
	private String accountsMobileNo;

	@Column(name = "accounts_email")
	private String accountsEmail;

	@Column(name = "company_type")
	private String companyType;

	@Column(name = "is_under_msme")
	private String isUnderMsme;

	@Transient
	private boolean status;

	@Transient
	private Integer sortStatus;

	@Transient
	private boolean formFilled;

	@Transient
	private String parseDate;

	@Transient
	private String filledDate;

	@Transient
	private Boolean isEmailSend;

}
